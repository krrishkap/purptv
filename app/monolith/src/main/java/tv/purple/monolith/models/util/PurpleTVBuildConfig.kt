package tv.purple.monolith.models.util

import java.util.*

data class PurpleTVBuildConfig(
    val number: Int = 0,
    val timestamp: Long = 0,
    val version: Int = 0,
    val revision: Int = 0,
    val apiKey: String? = null,
    val codename: String = "Monolith",
) {
    fun timestampToDate(): Date? {
        if (timestamp <= 0) {
            return null
        }

        return Date(timestamp.toLong() * 1000)
    }

    fun getVersion(): String {
        val mj = version.div(100)
        val mv = (version % 100).toString().padStart(2, '0')
        return "$mj.$mv"
    }
}
