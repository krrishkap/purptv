package tv.purple.monolith.features.updater.service

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import tv.purple.monolith.core.util.FileUtil.deleteDir
import tv.purple.monolith.core.util.NetUtil
import tv.purple.monolith.core.util.PackageHelperUtil
import tv.purple.monolith.features.updater.Updater
import java.io.File
import java.net.URL
import java.util.concurrent.Executors

class DownloadAndInstallApk : Service(), NetUtil.DownloadCallback {
    private lateinit var notifyHelper: NotificationHelper
    private val executor = Executors.newSingleThreadExecutor()

    private var isStopped: Boolean = false

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (intent == null || !intent.hasExtra(URL_KEY)) {
            stopSelf(startId)
            return super.onStartCommand(intent, flags, startId)
        }

        val url = intent.getStringExtra(URL_KEY) ?: ""
        if (url.isEmpty()) {
            stopSelf(startId)
            return super.onStartCommand(intent, flags, startId)
        }

        val build = intent.getIntExtra(BUILD_KEY, -1)
        notifyHelper = NotificationHelper(applicationContext)
        notifyHelper.initialize(this)
        downloadAndInstallApk(url, build)

        return START_NOT_STICKY
    }

    private fun downloadAndInstallApk(url: String, orgBuild: Int) {
        executor.execute {
            notifyHelper.prepareToDownloading()
            val build = orgBuild.let { if (it < 0) 0 else it }
            cleanup()

            val file = createTempFile()
            try {
                NetUtil.download(URL(url), file, this)
            } catch (th: Throwable) {
                notifyHelper.cancel()
                return@execute
            }
            if (isStopped) {
                notifyHelper.cancel()
                return@execute
            }

            val ota = getOtaFile(build)
            if (ota.exists()) {
                ota.delete()
            }

            file.copyTo(ota)
            file.delete()
            notifyHelper.prepareToInstall(ota)
            PackageHelperUtil.installApk(applicationContext, ota)
        }
    }

    private fun clearTempOtaBuild() {
        val file = getOtaFile(0)
        if (file.exists()) {
            file.delete()
        }
    }

    private fun createTempFile(): File {
        return File("")
//        return Updater.get().createTempFile()
    }

    private fun getOtaFile(build: Int): File {
        return File("")
//        return Updater.get().getOtaFile(build)
    }

    private fun clearTempDir() {
        Updater.getTempDir(applicationContext).deleteDir()
    }

    override fun onProgressUpdate(progress: Int, downloadedBytes: Int, totalBytes: Int) {
        notifyHelper.onDownloadProgress(progress, downloadedBytes, totalBytes)
    }

    override fun isDownloadCanceled(): Boolean {
        return isStopped
    }

    override fun onDestroy() {
        isStopped = true
        notifyHelper.cancel()
        cleanup()
        super.onDestroy()
    }

    private fun cleanup() {
        clearTempOtaBuild()
        clearTempDir()
    }

    companion object {
        const val URL_KEY = "URL"
        const val BUILD_KEY = "BUILD"

        fun startService(context: Context, url: String, build: Int) {
            context.startService(Intent(context, DownloadAndInstallApk::class.java).apply {
                putExtra(URL_KEY, url)
                putExtra(BUILD_KEY, build)
            })
        }

        fun cancelDownloading(context: Context) {
            context.stopService(Intent(context, DownloadAndInstallApk::class.java))
        }
    }
}