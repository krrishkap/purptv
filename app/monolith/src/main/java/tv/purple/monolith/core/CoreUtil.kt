package tv.purple.monolith.core

import android.app.Activity
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import android.widget.Toast
import tv.purple.monolith.core.models.flag.Flag
import tv.twitch.android.app.core.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.system.exitProcess


@Singleton
class CoreUtil @Inject constructor() {
    companion object {
        fun killApp() {
            exitProcess(0);
        }

        fun showToast(msg: String) {
            Handler(Looper.getMainLooper()).post {
                Toast.makeText(
                    ApplicationContext.getInstance().getContext(),
                    msg,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        fun devToast(context: Context, msg: String) {
            if (!Flag.DEV_MODE.asBoolean()) {
                return
            }

            Handler(Looper.getMainLooper()).post {
                Toast.makeText(
                    context,
                    msg,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        fun restart(activity: Activity) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                newRestartMethod(activity)
            } else {
                oldRestartMethod(activity)
            }

            killApp()
        }

        fun openUrl(context: Context, url: String) {
            if (url.isBlank()) {
                return
            }

            context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            })
        }

        private fun oldRestartMethod(activity: Activity) {
            val intent: Int = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            } else {
                PendingIntent.FLAG_UPDATE_CURRENT
            }
            val manager = (activity.getSystemService(Context.ALARM_SERVICE) as AlarmManager)
            manager.setExact(
                AlarmManager.ELAPSED_REALTIME,
                1500L,
                PendingIntent.getActivity(
                    activity,
                    0, Intent(
                        activity,
                        activity::class.java
                    ),
                    intent
                )
            )
        }

        private fun newRestartMethod(activity: Activity) {
            val pm = activity.baseContext.packageManager
            val pn = activity.baseContext.packageName
            pm.getLaunchIntentForPackage(pn)?.let { intent: Intent ->
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                activity.startActivity(intent)
            }
        }

        fun vibrate(context: Context, delay: Int, duration: Int) {
            Handler(context.mainLooper).postDelayed(
                {
                    intVibrate(
                        context = context,
                        duration = duration
                    )
                },
                delay.toLong()
            )
        }

        private fun intVibrate(context: Context, duration: Int) {
            val v = getVibrator(context = context)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                v?.vibrate(
                    VibrationEffect.createOneShot(
                        duration.toLong(),
                        VibrationEffect.DEFAULT_AMPLITUDE
                    )
                )
            } else {
                @Suppress("DEPRECATION")
                v?.vibrate(duration.toLong())
            }
        }

        private fun getVibrator(context: Context): Vibrator? {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                (context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager?)?.defaultVibrator
            } else {
                context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator?
            }
        }
    }
}