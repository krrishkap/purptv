package tv.purple.monolith.core.util

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.core.content.FileProvider
import tv.purple.monolith.core.LoggerImpl
import java.io.File

object PackageHelperUtil {
    private const val ANDROID_PACKAGE_ARCHIVE = "application/vnd.android.package-archive"

    fun installApk(
        context: Context,
        file: File,
        authority: String = context.packageName + ".provider"
    ): Boolean {
        if (!file.exists() || !file.canRead()) {
            LoggerImpl.error("File not found or can't be read: ${file.absolutePath}")
            return false
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O && !canInstallApk(context)) {
            LoggerImpl.warning("App lacks permission to install unknown sources.")
            return false
        }

        val intent = getInstallApkIntent(context, file, authority)
        try {
            context.startActivity(intent)
            return true
        } catch (e: ActivityNotFoundException) {
            LoggerImpl.error("No activity found to handle APK installation: ${e.message}")
            return false
        }
    }

    fun getInstallApkIntent(
        context: Context,
        file: File,
        authority: String = context.packageName + ".provider"
    ): Intent {
        val intent = Intent(Intent.ACTION_VIEW).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }

        val uri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            FileProvider.getUriForFile(context, authority, file)
        } else {
            Uri.fromFile(file)
        }

        intent.setDataAndType(uri, ANDROID_PACKAGE_ARCHIVE)
        return intent
    }

    fun canInstallApk(context: Context): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.packageManager.canRequestPackageInstalls()
        } else {
            true
        }
    }
}
