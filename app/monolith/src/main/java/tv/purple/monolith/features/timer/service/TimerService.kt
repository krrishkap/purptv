package tv.purple.monolith.features.timer.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.pm.ServiceInfo.FOREGROUND_SERVICE_TYPE_MEDIA_PLAYBACK
import android.os.Build
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import androidx.core.app.NotificationCompat
import tv.purple.monolith.bridge.RES_STRINGS
import tv.purple.monolith.core.CoreUtil
import tv.purple.monolith.core.ResManager.fromResToDrawableId
import tv.purple.monolith.core.ResManager.fromResToString
import java.util.Locale

class TimerService : Service() {
    companion object {
        private var isRunning = false

        private const val NOTIFICATION_CHANNEL_ID = "OrangeTV"
        private const val NOTIFICATION_TIMER_ID = 799

        private const val ACTION_CANCEL = "tv.purple.service.action.TIMER_CANCEL"
        private const val ACTION_ADD = "tv.purple.service.action.TIMER_ADD"

        private const val EXTRA_SECONDS = "tv.purple.service.extra.TIMER_SECONDS"


        fun createAndStartService(context: Context, totalSeconds: Int) {
            val intent = Intent(context, TimerService::class.java)
            intent.putExtra(EXTRA_SECONDS, totalSeconds)
            context.startService(intent)
        }

        fun createAddTimeIntent(context: Context, seconds: Int): Intent {
            return Intent(context, TimerService::class.java).apply {
                action = ACTION_ADD
                putExtra(EXTRA_SECONDS, seconds)
            }
        }

        fun createStopServiceIntent(context: Context): Intent {
            return Intent(context, TimerService::class.java).apply {
                action = ACTION_CANCEL
            }
        }

        private fun formatTime(seconds: Int): String {
            val minutes = seconds / 60 % 60
            val secs = seconds % 60
            val hours = seconds / 3600
            return String.format(Locale.ENGLISH, "%02d:%02d:%02d", hours, minutes, secs)
        }
    }

    private var totalSeconds: Int = 0
    private var remainingSeconds: Int = 0

    private lateinit var handler: Handler
    private lateinit var runnable: Runnable

    private val notificationManager: NotificationManager
        get() = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            ACTION_CANCEL -> {
                stopSelf()
                return START_STICKY
            }

            ACTION_ADD -> {
                val extraSeconds = intent.getIntExtra(EXTRA_SECONDS, 0)
                remainingSeconds += extraSeconds
                updateNotification()
                return START_STICKY
            }

            else -> {
                if (isRunning) {
                    return START_STICKY
                }
                totalSeconds = intent?.getIntExtra(EXTRA_SECONDS, 0) ?: 0
                isRunning = true
                remainingSeconds = totalSeconds
            }
        }

        setupNotificationChannel()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            startForeground(
                NOTIFICATION_TIMER_ID,
                buildNotification(),
                FOREGROUND_SERVICE_TYPE_MEDIA_PLAYBACK
            )
        } else {
            startForeground(
                NOTIFICATION_TIMER_ID,
                buildNotification()
            )
        }
        setupTimer()
        updateNotification()

        return START_STICKY
    }

    private fun setupTimer() {
        handler = Handler(Looper.getMainLooper())
        runnable = object : Runnable {
            override fun run() {
                if (remainingSeconds > 0) {
                    remainingSeconds--
                    updateNotification()
                    handler.postDelayed(this, 1000)
                } else {
                    stopSelf()
                    CoreUtil.killApp()
                }
            }
        }
        handler.postDelayed(runnable, 1000)
    }

    private fun setupNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                NOTIFICATION_CHANNEL_ID,
                "purpletv_timer_notify_channel".fromResToString(),
                NotificationManager.IMPORTANCE_HIGH
            )
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun buildNotification(): Notification {
        val flags = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_ONE_SHOT
        } else {
            0
        }

        return NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
            .setContentTitle(RES_STRINGS.purpletv_timer_title.fromResToString())
            .setContentText(formatTime(remainingSeconds))
            .setSmallIcon(android.R.drawable.ic_dialog_alert)
            .addAction(
                "ic_cancel".fromResToDrawableId(),
                RES_STRINGS.purpletv_timer_cancel.fromResToString(),
                PendingIntent.getService(
                    baseContext,
                    1,
                    createStopServiceIntent(
                        context = baseContext
                    ), flags
                )
            )
            .addAction(
                "ic_add".fromResToDrawableId(), "+10m",
                PendingIntent.getService(
                    baseContext,
                    2,
                    createAddTimeIntent(
                        context = baseContext,
                        seconds = 10 * 60
                    ), flags
                )
            ).addAction(
                "ic_add".fromResToDrawableId(), "+1h",
                PendingIntent.getService(
                    baseContext,
                    3,
                    createAddTimeIntent(
                        context = baseContext,
                        seconds = 60 * 60
                    ), flags
                )
            )
            .setProgress(totalSeconds, remainingSeconds, false)
            .setAutoCancel(false)
            .setOngoing(true)
            .setOnlyAlertOnce(true)
            .build()
    }

    private fun updateNotification() {
        notificationManager.notify(NOTIFICATION_TIMER_ID, buildNotification())
    }

    override fun onDestroy() {
        handler.removeCallbacks(runnable)
        notificationManager.cancel(NOTIFICATION_TIMER_ID)
        isRunning = false
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? = null
}