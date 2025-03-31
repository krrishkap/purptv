package tv.purple.monolith.models.util

interface ILogger {
    fun getTag(): String
    fun warning(messageText: String?)
    fun info(messageText: String?)
    fun debug(messageText: String?)
    fun error(messageText: String?)
    fun debug(invoke: () -> String)
}