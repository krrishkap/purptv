import os
import shutil
import subprocess
from pathlib import Path

from src.model.data import Env, Context
from src.task.base import BaseTask
from src.util import get_files, copyfile

PATCH_DIR = "tv.twitch.android.app"


class GeneratePatches(BaseTask):
    """
    Генерирует патчи из текущих изменений в декомпилированном APK.
    """
    __TASK_NAME__ = "GEN_PATCHES"

    def run(self, env: Env):
        apk_dir = self.ctx().apk_dir
        subprocess.run(["git", "add", "."], cwd=apk_dir)
        with subprocess.Popen(["git", "diff", "HEAD", "--minimal", "--ignore-space-at-eol"],
                              cwd=apk_dir,
                              stdout=subprocess.PIPE, stderr=subprocess.PIPE) as p:
            splits = split_out(p.stdout.read())
            gen_patches(splits, env.patches_dir.joinpath(PATCH_DIR))


class CreateGitRepo(BaseTask):
    """
    Инициализирует Git репозиторий в директории с декомпилированным APK.

    Создает Git репозиторий, добавляет все файлы и делает начальный коммит.
    Это позволяет отслеживать изменения и генерировать патчи.
    """
    __TASK_NAME__ = "GIT_INIT"

    def run(self, env: Env):
        apk_dir = self.ctx().apk_dir
        copyfile(env.bin_dir.joinpath("gitignore"), apk_dir.joinpath(".gitignore"))
        subprocess.run(["git", "init"], cwd=apk_dir)
        subprocess.run(["git", "add", "."], cwd=apk_dir)
        subprocess.run(["git", "commit", "-m", "init"], cwd=apk_dir)


class Restore(BaseTask):
    """
    Восстанавливает все файлы в их исходное состояние.

    Сбрасывает все изменения и восстанавливает файлы до состояния
    последнего коммита (git restore).
    """
    __TASK_NAME__ = "GIT_RESTORE"

    def run(self, env: Env):
        subprocess.run(["git", "reset"], cwd=self.ctx().apk_dir)
        subprocess.run(["git", "restore", "*"], cwd=self.ctx().apk_dir)


class Reset(BaseTask):
    """
    Сбрасывает индекс Git без изменения рабочих файлов.

    Выполняет команду git reset, которая отменяет добавление файлов в индекс,
    но сохраняет изменения в рабочей директории.
    """
    __TASK_NAME__ = "GIT_RESET"

    def run(self, env: Env):
        subprocess.run(["git", "reset"], cwd=self.ctx().apk_dir)


class ApplyPatches(BaseTask):
    """
    Применяет патчи к декомпилированному APK.

    Применяет все патчи из директории patches к текущему декомпилированному APK.
    Может работать в режиме проверки (--check) для выявления конфликтов.
    """
    __TASK_NAME__ = "APPLY_PATCHES"

    def __init__(self, context: Context, check: bool):
        super().__init__(context)
        self._check = check
        if check:
            self.__NAME__ = "CHECK_PATCHES"

    def run(self, env: Env):
        patches = env.patches_dir.joinpath(PATCH_DIR)
        if not patches.exists():
            print("Patches not found")
            return

        total = 0
        ok = 0
        for file in get_files(patches):
            if file.name.endswith(".ico") or file.name.endswith(".ini"):
                continue
            try:
                total += 1
                args = ["git", "apply", "--ignore-space-change", "--ignore-whitespace", file.as_posix()]
                if self._check:
                    args.append("--check")
                subprocess.run(args, cwd=self.ctx().apk_dir, shell=True, check=True, capture_output=True)
                if not self._check:
                    print("{}: OK".format(file.name))
                ok += 1
            except Exception as e:
                if self._check:
                    try:
                        error = e.stderr.decode()
                    except AttributeError:
                        error = str(e)

                    print("{}\nFAILED [{}] --> \n{}{}".format('=' * 25, file.name, error, '=' * 25))
                else:
                    print("{}: ERROR".format(file.name))

        print(f"Total patches: {total}, OK: {ok}, FAILED: {total - ok}")


def split_out(out):
    diffs = []
    diff = []
    for l in out.splitlines():
        if l.startswith(b'diff --git '):
            if diff:
                diffs.append(diff)
                diff = []
            diff.append(l)
        else:
            diff.append(l)
    if diff:
        diffs.append(diff)

    return diffs


def get_filename(split):
    l = split[0].decode().split()[2][2:]
    if l.startswith("smali/") or l.startswith("smali_"):
        l = l.split("/", 1)[1]

    return l.replace("/", ".") + ".patch"


def safe_get_out_path(out_dir, split):
    l = split[0].decode().split()[2][2:]
    if l.startswith("smali/") or l.startswith("smali_"):
        path = Path(out_dir).joinpath("smali").joinpath(l.split("/", 1)[1] + ".patch")
    else:
        path = Path(out_dir).joinpath(l + ".patch")

    if not path.parent.exists():
        os.makedirs(path.parent.as_posix())

    return path


def gen_patches(splits, out_dir):
    if not out_dir.exists():
        os.makedirs(out_dir.as_posix())

    for split in splits:
        filename = get_filename(split)
        path = safe_get_out_path(out_dir, split)
        with open(path, 'wb') as fp:
            for index, line in enumerate(split):
                if index == 1 and line.startswith(b'index '):
                    continue
                fp.write(line)
                fp.write(b'\n')
        print("gen::{}".format(filename))

    print(f"gen::total={len(splits)}")
