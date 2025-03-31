import dataclasses
from pathlib import Path


@dataclasses.dataclass
class Env:
    apktool_jar: Path
    uber_jar: Path
    zipalign_exe: Path
    out_dir: Path
    bin_dir: Path
    app_dir: Path
    patches_dir: Path
    lib_dir: Path
    d2j_dir: Path
    build_config: Path
    donations_file: Path


@dataclasses.dataclass
class ApkDescriptor:
    file: Path
    version_code: int
    version_name: str


@dataclasses.dataclass
class Context:
    apk_desc: ApkDescriptor
    apk_dir: Path
    out_apk: Path
    cached_apk: Path = None
