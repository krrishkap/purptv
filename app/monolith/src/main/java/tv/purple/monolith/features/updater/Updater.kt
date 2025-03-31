package tv.purple.monolith.features.updater

import android.content.Context
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import tv.purple.monolith.bridge.PurpleTVAppContainer.Companion.getInstance
import tv.purple.monolith.core.CoreUtil
import tv.purple.monolith.core.ResManager.fromResToString
import tv.purple.monolith.core.ResManager.fromResToStringId
import tv.purple.monolith.core.models.flag.Flag
import tv.purple.monolith.core.models.flag.variants.UpdateChannel
import tv.purple.monolith.core.util.FileUtil.deleteDir
import tv.purple.monolith.core.util.PurpleBuildConfigUtil
import tv.purple.monolith.features.updater.component.data.model.UpdateData
import tv.purple.monolith.features.updater.component.data.repository.UpdaterRepository
import tv.purple.monolith.features.updater.data.view.UpdaterActivity
import tv.purple.monolith.features.updater.service.DownloadAndInstallApk
import tv.twitch.android.util.Optional
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Updater @Inject constructor(
    private val updaterRepository: UpdaterRepository,
    private val context: Context
) {
    private val disposables = CompositeDisposable()

    private var updateData: Optional<UpdateData> = Optional.empty()

    init {
        DownloadAndInstallApk.cancelDownloading(context)
    }

    companion object {
        const val TEMP_OTA_DIR = "tmp_ota"
        const val INSTALL_OTA_DIR = "install_ota"

        @JvmStatic
        fun get(): Updater {
            return getInstance().provideUpdater()
        }

        fun getTempDir(context: Context): File {
            val tmp = File(context.cacheDir, TEMP_OTA_DIR)
            if (tmp.exists()) {
                return tmp
            }
            tmp.mkdir()

            return tmp
        }

        fun getOtaDir(context: Context): File {
            val ota = File(context.cacheDir, INSTALL_OTA_DIR)
            if (ota.exists()) {
                return ota
            }
            ota.mkdir()

            return ota
        }

        @JvmStatic
        val orangeAppUpdateAvailable: Int
            get() = "orange_app_update_available".fromResToStringId()

        @JvmStatic
        val orangeAppUpdateAvailableCta: Int
            get() = "orange_app_update_available_cta".fromResToStringId()
    }

    fun onClearCacheClicked(context: Context) {
        clearTempCache(context = context)
        clearOtaDir(context = context)

        CoreUtil.showToast("orange_updater_done".fromResToString())
    }

    fun onCheckUpdateClicked(context: Context) {
        clearTempCache(context = context)
        when (Flag.UPDATE_CHANNEL.asVariant<UpdateChannel>()) {
            UpdateChannel.Disabled -> {
                CoreUtil.showToast(
                    "orange_updater_channel_disabled".fromResToString()
                )
                return
            }

            else -> {
                maybeStartActivity(context = context)
            }
        }
    }

    fun onBannerInstallUpdateClicked(context: Context) {
        updateData.ifPresent { data ->
            UpdaterActivity.startActivity(context = context, data = data)
        }
    }

    private fun maybeStartActivity(context: Context) {
        disposables.add(
            updaterRepository.observeUpdate()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ optionalData ->
                    checkUpdateData(context, optionalData)
                }, Throwable::printStackTrace)
        )
    }

    private fun checkUpdateData(
        context: Context,
        optional: Optional<UpdateData>
    ) {
        if (!optional.isPresent) {
            CoreUtil.showToast("orange_updater_not_found".fromResToString())
            return
        }

        optional.ifPresent { data ->
            if (!shouldShowPrompt(optional)) {
                CoreUtil.showToast(
                    "orange_updater_latest_version".fromResToString()
                )
                return@ifPresent
            }

            UpdaterActivity.startActivity(context = context, data = data)
        }
    }

    fun clearTempCache(context: Context) {
        getTempDir(context = context).deleteDir()
    }

    fun clearOtaDir(context: Context) {
        getOtaDir(context = context).deleteDir()
    }

    fun createTempFile(): File {
        return File(getTempDir(context), "${System.currentTimeMillis()}.tmp")
    }

    fun getOtaFile(build: Int): File {
        return File(getOtaDir(context), "$build.apk")
    }

//    fun injectToUpdatePromptPresenter(
//        updatePromptPresenter: UpdatePromptPresenter,
//        listenerBehaviorSubject: BehaviorSubject<Optional<UpdatePromptPresenter.UpdatePromptPresenterListener>>
//    ) {
//        ISubscriptionHelper.DefaultImpls.`autoDispose$default`(
//            updatePromptPresenter,
//            updaterRepository.observeUpdate()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .flatMapObservable { updateData ->
//                    updatePromptPresenter.onActiveObserver().toObservable().switchMap { status ->
//                        listenerBehaviorSubject.map { listenerOptional ->
//                            Triple(updateData, status, listenerOptional)
//                        }
//                    }
//                }.subscribe({
//                    updateData = it.first
//                    if (it.second) {
//                        it.third.ifPresent { listener ->
//                            if (shouldShowPrompt(it.first)) {
//                                listener.updateDownloadedAndReadyToInstall()
//                            }
//                        }
//                    }
//                }, {
//                    it.printStackTrace()
//                }),
//            null,
//            1,
//            null
//        )
//        updatePromptPresenter.onActiveObserver()
//    }

    private fun shouldShowPrompt(first: Optional<UpdateData>): Boolean {
        if (!first.isPresent) {
            return false
        }

        return !(!Flag.FORCE_OTA.asBoolean() && (first.get().build <= PurpleBuildConfigUtil.buildConfigVariant.number))
    }
}