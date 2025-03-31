import argparse
import json
import os
from datetime import datetime
from os import mkdir
from pathlib import Path

from pyaxmlparser import APK

from src.di import ServiceLocator
from src.model.data import Env, ApkDescriptor, Context
from src.task import apktool, internal, git, d2j
from src.task.base import BaseTask
from src.util import get_working_directory, get_safe_path, done, print_title, die


def parse_file_arg(arg: str):
    if not arg:
        raise Exception("file is null")

    arg = arg.strip()
    if not arg:
        raise Exception("file is empty")

    if os.path.isabs(arg):
        fpath = Path(arg)
    else:
        if not arg.startswith("/apk") and not arg.startswith("\\apk"):
            arg = "apk{}{}".format(os.path.sep, arg)

        fpath = Path(get_working_directory(), arg)

    if fpath.exists() and fpath.is_file():
        return fpath

    raise FileNotFoundError(arg)


def parse_env():
    wd = get_working_directory()
    with open(os.path.join(wd, "etc/env.json"), "r", encoding="utf-8") as fp:
        js = json.load(fp)

    apktool = get_safe_path(os.path.join("bin", js['apktool']))
    zipalign = get_safe_path(os.path.join("bin", js['zipalign']))
    uber = get_safe_path(os.path.join("bin", js['uber']))

    return Env(apktool_jar=apktool,
               out_dir=wd.joinpath("out"),
               bin_dir=Path(wd, "bin"),
               patches_dir=Path(wd, "patches"),
               zipalign_exe=zipalign,
               app_dir=Path(wd).parent.joinpath("app"),
               lib_dir=Path(wd).parent.joinpath("lib"),
               build_config=Path(wd, "etc").joinpath("build.json"),
               donations_file=Path(wd, "etc").joinpath("donations.txt"),
               d2j_dir=Path(wd, "bin/dex2jar"),
               uber_jar=uber)


def get_apk_desc(src: Path):
    apk_container = APK(src.as_posix())
    if not apk_container.is_valid_APK():
        raise Exception("Invalid APK: {}".format(src))

    return ApkDescriptor(file=src,
                         version_name=apk_container.version_name,
                         version_code=apk_container.version_code)


def run(tasks: [BaseTask]):
    if not tasks:
        done("Zzzz...")

    env = ServiceLocator.get_instance().get(Env.__class__)
    for task in tasks:
        try:
            print_title("[TASK] %s" % task.__TASK_NAME__)
            task.run(env=env)
            print("[TASK] %s: ✅" % task.__TASK_NAME__)
        except Exception as e:
            print("[TASK] %s: ❌" % task.__TASK_NAME__)
            print(e)
            raise e


def create_decompile_tasks(tasks, ctx: Context, verbose=False):
    tasks.append(internal.Cleanup(ctx))
    tasks.append(apktool.DecompileApk(ctx, verbose=verbose))
    tasks.append(apktool.FixAnnotations(ctx))
    tasks.append(apktool.FixColors(ctx))
    tasks.append(apktool.FixLauncherIcon(ctx))
    tasks.append(apktool.FixDexBuilding(ctx))
    tasks.append(git.CreateGitRepo(ctx))


def create_recompile_tasks(tasks,
                           ctx: Context,
                           skip_app=False,
                           skip_apktool=False,
                           x64=False,
                           verbose=False,
                           debuggable=False):
    if not skip_apktool:
        tasks.append(internal.CopySo(ctx))
        tasks.append(internal.InjectRes(ctx))
        tasks.append(internal.InjectDonations(ctx))
        if x64:
            tasks.append(internal.X64(ctx))
    tasks.append(internal.IncreaseBuildNumber(ctx))
    if not skip_apktool:
        tasks.append(apktool.RecompileApk(ctx, verbose=verbose, debuggable=debuggable))
    else:
        tasks.append(apktool.CachedApk(ctx))
    if not skip_app:
        tasks.append(internal.BuildAppDex(ctx))
    tasks.append(internal.InjectDex(ctx))
    tasks.append(apktool.UberSignApk(ctx))


def handle_args(ctx: Context):
    tasks = []

    apk_dir = ctx.apk_dir
    if args.decompile:
        if apk_dir.exists():
            die("Use --force")

        create_decompile_tasks(tasks, ctx=ctx, verbose=args.verbose)
    if args.jar:
        tasks.append(d2j.GenerateJarFile(ctx))
    if args.check:
        tasks.append(git.ApplyPatches(ctx, check=True))
    if args.generate:
        tasks.append(git.GeneratePatches(ctx))
    if args.apply:
        tasks.append(git.ApplyPatches(ctx, check=False))
    if args.recompile:
        create_recompile_tasks(tasks, ctx, args.skip_app, args.skip_apktool, args.x64, args.verbose, args.debuggable)
    if args.restore:
        tasks.append(git.Restore(ctx))
    if args.reset:
        tasks.append(git.Reset(ctx))
    if args.make:
        if apk_dir.exists() and not args.force:
            die("Use --force")

        create_decompile_tasks(tasks, ctx=ctx, verbose=args.verbose)
        tasks.append(git.ApplyPatches(ctx, check=False))
        create_recompile_tasks(tasks, ctx, args.skip_app, args.skip_apktool, args.x64, args.verbose, args.debuggable)

    if args.install:
        tasks.append(internal.Install(ctx))

    run(tasks=tasks)


def get_apk_filepath(path):
    if path:
        return path

    apk_dir = Path(get_working_directory()).joinpath('apk')
    if not apk_dir.exists():
        mkdir(apk_dir)

    for p in apk_dir.iterdir():
        if p.is_file() and p.name.endswith('.apk'):
            return p

    die("apk not found")


def create_context(ad: ApkDescriptor):
    apk_dir = Path(ad.file.parent, ad.file.stem)
    e = ServiceLocator.get_instance().get(Env.__class__)
    return Context(
        apk_desc=ad,
        apk_dir=apk_dir,
        out_apk=Path(e.out_dir, ad.file.name),
        cached_apk=Path(apk_dir).joinpath("build/cached.apk")
    )


def parse_args():
    parser = argparse.ArgumentParser(description='PurpleTV Toolchain')
    parser.add_argument('-f', '--file', type=parse_file_arg,
                        help='APK file to process (if not specified, uses first APK in apk/ directory)')
    parser.add_argument('--install', action='store_true', help='Install the APK on connected device after building')
    parser.add_argument('--x64', action='store_true',
                        help='Build for x64 architecture only (removes other architecture libraries)')
    parser.add_argument('--verbose', action='store_true', help='Verbose output')
    parser.add_argument('--debuggable', action='store_true', help='Build debuggable APK')

    compile_options_group = parser.add_mutually_exclusive_group()
    compile_options_group.add_argument('--skip_app', action='store_true', help='Skip building the dex file')
    compile_options_group.add_argument('--skip_apktool', action='store_true',
                                       help='Skip using Apktool to recompile the APK. Use cached apk instead')

    task_group = parser.add_mutually_exclusive_group()
    task_group.add_argument('--decompile', action='store_true', help="Decompile an APK")
    task_group.add_argument('--jar', action='store_true', help="Generate a JAR file from the APK")
    task_group.add_argument('--check', action='store_true', help="Check if patches can be applied without errors")
    task_group.add_argument('--generate', action='store_true', help="Generate patches from current modifications")
    task_group.add_argument('--apply', action='store_true',
                            help="Apply patches to decompiled APK from patches directory")
    task_group.add_argument('--recompile', action='store_true', help="Recompile apk")
    task_group.add_argument('--restore', action='store_true',
                            help='Restore all files to their original state (git restore)')
    task_group.add_argument('--reset', action='store_true', help='Reset staged changes (git reset)')
    task_group.add_argument('--make', action='store_true', help="Decompile, patch and build in one step")

    return parser.parse_args()


if __name__ == '__main__':
    env = parse_env()

    ServiceLocator.get_instance().register(Env.__class__, env)

    start_profile = datetime.now()
    args = parse_args()

    apk_path = get_apk_filepath(args.file)
    print(f"Current APK: `{apk_path.as_posix()}`")

    apk_desc = get_apk_desc(apk_path)

    context = create_context(ad=apk_desc)

    handle_args(ctx=context)

    end_profile = datetime.now()
    print("Total: {}s".format((end_profile - start_profile).total_seconds()))
