import os
import shutil
import sys
from pathlib import Path


def get_path(src):
    if os.path.isabs(src):
        return Path(src)
    else:
        return Path(get_working_directory(), src)


def get_safe_path(src):
    fpath = get_path(src)

    if not fpath.exists():
        raise FileNotFoundError(fpath)

    return fpath


def get_working_directory():
    return Path(os.getcwd())


def done(msg):
    print(msg)
    sys.exit(0)


def die(msg):
    print(msg)
    sys.exit(1)


def print_title(name, size=100):
    c = size - len(name)
    l_half = c // 2
    r_half = c - l_half
    print("*" * l_half, name, "*" * r_half)


def get_files(path: Path):
    if path.is_file():
        yield path
    else:
        for i in path.iterdir():
            yield from get_files(i)


def copyfile(src, dest):
    src = Path(src)
    dest = Path(dest)

    if not src.exists():
        raise FileNotFoundError(src)

    shutil.copyfile(src.as_posix(), dest.as_posix())


def rm(src, ignore_errors=False):
    src = Path(src)
    if not src.exists() and not ignore_errors:
        raise FileNotFoundError(src)

    shutil.rmtree(src.as_posix(), ignore_errors=ignore_errors)


def copytree(src, dst, ignore_errors=False, ignore_exists=True):
    src = Path(src)
    dst = Path(dst)

    if not src.exists() and not ignore_errors:
        raise FileNotFoundError(src)

    if dst.exists() and not ignore_errors:
        if not ignore_exists:
            raise FileExistsError(dst)

    shutil.copytree(src.as_posix(), dst.as_posix(), dirs_exist_ok=ignore_exists)
