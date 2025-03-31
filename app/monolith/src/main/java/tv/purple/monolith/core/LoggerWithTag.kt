package tv.purple.monolith.core

import tv.purple.monolith.models.util.ILogger


class LoggerWithTag(
    private val tag: String
) : ILogger {
    override fun getTag(): String {
        return LoggerImpl.getTag()
    }

    override fun warning(messageText: String?) {
        LoggerImpl.warning(
            messageText = "[$tag] $messageText"
        )
    }

    override fun info(messageText: String?) {
        LoggerImpl.info(
            messageText = "[$tag] $messageText"
        )
    }

    override fun debug(messageText: String?) {
        LoggerImpl.debug(
            messageText = "[$tag] $messageText"
        )
    }

    override fun error(messageText: String?) {
        LoggerImpl.error(
            messageText = "[$tag] $messageText"
        )
    }

    override fun debug(invoke: () -> String) {
        LoggerImpl.debug(
            invoke = invoke
        )
    }

    companion object {
        fun create(tag: String): ILogger {
            return LoggerWithTag(tag)
        }
    }
}