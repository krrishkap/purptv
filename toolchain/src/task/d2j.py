import subprocess

from src.config import RUN_JAVA_COMMAND
from src.model.data import Env
from src.task.base import BaseTask


from logging import getLogger

logger = getLogger(__name__)


def build_classpath(env: Env):
    libs = env.d2j_dir.joinpath("lib")
    return ";".join([i.as_posix() for i in libs.iterdir() if i.is_file() and i.name.endswith(".jar")])


class GenerateJarFile(BaseTask):
    __TASK_NAME__ = "DEX2JAR_GENERATE"
    OUT_PATH = "Twitch/libs/tv.twitch.android.app.jar"

    def run(self, env: Env):
        ctx = self.ctx()

        cp = build_classpath(env)
        jar_file = env.app_dir.joinpath(self.OUT_PATH)

        command = [*RUN_JAVA_COMMAND, "-cp", cp, "com.googlecode.dex2jar.tools.Dex2jarCmd",
                   ctx.apk_desc.file.as_posix(), "-o", jar_file.as_posix(), "--force"]
        logger.debug({'ctx': ctx, 'command': command})
        subprocess.run(command)
