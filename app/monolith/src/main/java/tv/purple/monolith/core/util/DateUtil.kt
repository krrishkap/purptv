package tv.purple.monolith.core.util

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone
import java.util.concurrent.TimeUnit

object DateUtil {
    private val dateFormats = listOf(
        "yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'",
        "yyyy-MM-dd'T'HH:mm:ss'Z'"
    )

    fun getStandardizeDateString(str: String): Date? {
        dateFormats.forEach { format ->
            try {
                return SimpleDateFormat(format, Locale.US).apply {
                    timeZone = TimeZone.getTimeZone("UTC")
                }.parse(str)
            } catch (_: ParseException) { }
        }

        return null
    }

    fun getSecondsBetween(start: Date, end: Date): Long =
        TimeUnit.MILLISECONDS.toSeconds(end.time - start.time)

    fun getSecondsBetween(start: Long, end: Long): Long =
        TimeUnit.MILLISECONDS.toSeconds(end - start)

    fun toSeconds(milliseconds: Long): Long = TimeUnit.MILLISECONDS.toSeconds(milliseconds)

    fun isSameDate(d1: Date?, d2: Date?): Boolean {
        if (d1 == null || d2 == null) {
            return false
        }

        val c1 = Calendar.getInstance().apply { time = d1 }
        val c2 = Calendar.getInstance().apply { time = d2 }

        return c1[Calendar.YEAR] == c2[Calendar.YEAR] &&
                c1[Calendar.MONTH] == c2[Calendar.MONTH] &&
                c1[Calendar.DAY_OF_MONTH] == c2[Calendar.DAY_OF_MONTH]
    }
}