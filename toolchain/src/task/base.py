import abc

from src.model.data import Context, Env


class BaseTask(metaclass=abc.ABCMeta):
    __TASK_NAME__ = "BASE"

    def __init__(self, context: Context):
        self.__ctx = context

    def ctx(self):
        return self.__ctx

    @abc.abstractmethod
    def run(self, env: Env):
        raise NotImplementedError()


class BaseTaskException(BaseException):
    pass
