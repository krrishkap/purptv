package tv.purple.monolith.features.vodhunter

import android.content.Context
import io.reactivex.Single
import retrofit2.Response
import tv.purple.monolith.bridge.RES_STRINGS
import tv.purple.monolith.bridge.ex.ISharePanelWidgetViewDelegate
import tv.purple.monolith.component.api.repository.NopRepository
import tv.purple.monolith.core.CoreUtil
import tv.purple.monolith.core.LoggerImpl
import tv.purple.monolith.core.ResManager.fromResToString
import tv.purple.monolith.core.compat.ClassCompat
import tv.purple.monolith.core.models.flag.Flag
import tv.purple.monolith.core.util.NetUtil
import tv.purple.monolith.core.util.ViewUtil.getView
import tv.twitch.android.core.mvp.viewdelegate.RxViewDelegate
import tv.twitch.android.shared.share.panel.SharePanelDefaultPresenter
import tv.twitch.android.shared.ui.elements.bottomsheet.InteractiveRowView
import javax.inject.Inject

class VodHunter @Inject constructor(
    private val nopRepository: NopRepository,
    private val context: Context
) {
    companion object {
        @JvmStatic
        val isEnabled: Boolean
            get() = Flag.VODHUNTER.asBoolean()
    }

    private fun hookVodManifestResponse(
        vodResponse: Single<Response<String>>,
        vodId: String
    ): Single<Response<String>> {
        if (!Flag.VODHUNTER.asBoolean()) {
            return vodResponse
        }

        return vodResponse.flatMap {
            when {
                it.isSuccessful -> return@flatMap vodResponse
                it.code() == 403 -> {
                    return@flatMap createSubFreePlaylist(
                        orgResponse = it,
                        vodId = vodId
                    )
                }

                else -> vodResponse
            }
        }
    }

    private fun createSubFreePlaylist(
        orgResponse: Response<String>,
        vodId: String
    ): Single<Response<String>> {
        val payload = ""
        LoggerImpl.debug("[test] payload --> $payload")
        return nopRepository.getVodHunterPlaylist(payload = payload)
            .doOnSuccess {
                CoreUtil.showToast(
                    "[VODHunter] ${RES_STRINGS.purpletv_vodhunter_hunting.fromResToString()}"
                )
            }
            .onErrorResumeNext { th: Throwable ->
                CoreUtil.showToast(
                    RES_STRINGS.purpletv_generic_error_d.fromResToString(
                        "VODHunter",
                        th.localizedMessage ?: "<empty>"
                    )
                )
                th.printStackTrace()
                Single.just(orgResponse)
            }
    }

    fun getDownloadClipButton(delegate: RxViewDelegate<*, *>): InteractiveRowView {
        return delegate.contentView.getView<InteractiveRowView>(resName = "share_panel_widget__download_clip")
            .apply {
                ClassCompat.invokeIf<ISharePanelWidgetViewDelegate>(obj = delegate) { proxy ->
                    setOnClickListener {
                        proxy.pushDownloadClipEvent()
                    }
                }
            }
    }

    fun tryDownloadClip(
        state: SharePanelDefaultPresenter.State.Initialized
    ) {
        val playable = state.sharePlayableModel ?: run {
            LoggerImpl.error("sharePlayableModel is null")
            return
        }

        if (playable is SharePanelDefaultPresenter.SharePlayable.Clip) {
            val model = playable.clipModel
            NetUtil.downloadClipLegacy(
                context = context,
                url = "", // FIXME: FIX
                filename = model.title.removeSuffix(".")
            )
        }
    }
}