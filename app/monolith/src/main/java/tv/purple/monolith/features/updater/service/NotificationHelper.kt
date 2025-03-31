package tv.purple.monolith.features.updater.service

import android.annotation.SuppressLint
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.os.Build
import android.text.format.Formatter
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import tv.purple.monolith.core.ResManager.fromResToString
import tv.purple.monolith.core.util.NotificationsUtil
import tv.purple.monolith.core.util.PackageHelperUtil
import java.io.File
import java.util.*

class NotificationHelper(
    private val context: Context
) {
    private lateinit var notificationManager: NotificationManagerCompat
    private lateinit var notificationBuilder: NotificationCompat.Builder

    private var lastNotify: Long = 0
    private var isRunning: Boolean = true


    fun initialize(service: Service) {
        notificationManager = NotificationManagerCompat.from(context)
        NotificationsUtil.createNotificationChannels(context)

        notificationBuilder = NotificationCompat.Builder(context, NotificationsUtil.CHANNEL_ID)
            .setSmallIcon(android.R.drawable.stat_sys_download)
            .setAutoCancel(false)
            .setOngoing(true)
            .setOnlyAlertOnce(true)
            .setContentTitle("orangetv_downloading".fromResToString())
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .setProgress(100, 0, true)

        service.startForeground(NotificationsUtil.UPDATER_ID, notificationBuilder.build())
    }

    fun prepareToDownloading() {
        notificationBuilder.setProgress(100, 0, true)
        notificationBuilder.setContentTitle("orangetv_downloading".fromResToString())
        notificationBuilder.setContentText(null)
        render()
    }

    private fun render(force: Boolean = false) {
        val currentTime = System.currentTimeMillis()
        if (force || lastNotify <= 0 || currentTime - lastNotify > MIN_NOTIFY_TIMEOUT) {
            updateNotification()
        }
    }

    @SuppressLint("MissingPermission")
    private fun updateNotification() {
        notificationManager.notify(NotificationsUtil.UPDATER_ID, notificationBuilder.build())
        lastNotify = System.currentTimeMillis()
    }

    fun prepareToInstall(ota: File) {
        notificationBuilder = NotificationCompat.Builder(context, NotificationsUtil.CHANNEL_ID)
            .setSmallIcon(android.R.drawable.stat_sys_download)
            .setOnlyAlertOnce(true)
            .setContentTitle("orangetv_install".fromResToString())
            .setPriority(NotificationCompat.PRIORITY_HIGH)


        val flags = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        } else {
            PendingIntent.FLAG_UPDATE_CURRENT
        }
        val pendingIntent = PendingIntent.getActivity(
            context,
            0,
            PackageHelperUtil.getInstallApkIntent(context, ota),
            flags
        )

        notificationBuilder.setContentIntent(pendingIntent)
        render(force = true)
    }

    fun cancel() {
        isRunning = false
        notificationManager.cancel(NotificationsUtil.UPDATER_ID)
    }

    fun onDownloadProgress(progress: Int, downloadedBytes: Int, totalBytes: Int) {
        notificationBuilder.setContentTitle("orangetv_downloading".fromResToString())
        notificationBuilder.setProgress(100, progress, false)
        notificationBuilder.setContentText(
            String.format(
                Locale.ENGLISH, "%1\$s/%2\$s",
                Formatter.formatFileSize(context, downloadedBytes.toLong()),
                Formatter.formatFileSize(context, totalBytes.toLong())
            )
        )
        render()
    }

    companion object {
        private const val MIN_NOTIFY_TIMEOUT = 200
    }
}