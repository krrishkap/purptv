package tv.purple.monolith.core.util

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import tv.purple.monolith.core.ResManager.fromResToString

object NotificationsUtil {
    const val CHANNEL_ID = "PurpleTV"
    const val UPDATER_ID = 700

    @JvmStatic
    fun createNotificationChannels(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val nc = NotificationChannel(
                CHANNEL_ID,
                "purpletv_notification_channel_name".fromResToString(),
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "purpletv_notification_channel_desc".fromResToString()
            }
            context.getSystemService(NotificationManager::class.java).createNotificationChannel(nc)
        }
    }
}