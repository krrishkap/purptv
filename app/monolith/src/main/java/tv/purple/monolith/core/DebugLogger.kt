package tv.purple.monolith.core

import android.annotation.SuppressLint
import android.util.Log

@SuppressLint("LogNotTimber")
object DebugLogger {
    private const val TAG = "NOP-DEBUG"
    @JvmStatic
    fun debugThrowable(th: Throwable?) {
        Log.e(TAG, "${Throwable().stackTrace[1]} :-> ", th)
        th?.printStackTrace()
    }

    @JvmStatic
    fun debugException(ex: Exception?) {
        Log.e(TAG, "${Throwable().stackTrace[1]} :-> ", ex)
        ex?.printStackTrace()
    }

    @JvmStatic
    fun debugObject(obj: Any?) {
        Log.e(TAG, "${Throwable().stackTrace[1]} :->  $obj")
        if (obj is Throwable) {
            debugThrowable(obj)
        }
    }

    @JvmStatic
    fun debug(int: Int) {
        Log.e(TAG, "${Throwable().stackTrace[1]} :-> $int")
    }

    @JvmStatic
    fun debugCrashlytics(str: String?, th: Throwable?) {
        Log.e(TAG, "${Throwable().stackTrace[1]} crashlytics [str] :-> $str")
        Log.e(TAG, "${Throwable().stackTrace[1]} crashlytics [th] :-> $th")
    }

    @JvmStatic
    fun debugMobileshield(i: Int, j: Long, str: String?) {
        Log.e(TAG, "i --> $i")
        Log.e(TAG, "j --> $j")
        Log.e(TAG, "str --> $str")
    }
}