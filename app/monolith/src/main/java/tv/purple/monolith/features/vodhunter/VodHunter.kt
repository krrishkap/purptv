package tv.purple.monolith.features.vodhunter

import io.reactivex.Single
import retrofit2.Response
import tv.purple.monolith.bridge.RES_STRINGS
import tv.purple.monolith.component.api.repository.NopRepository
import tv.purple.monolith.core.CoreUtil
import tv.purple.monolith.core.LoggerWithTag
import tv.purple.monolith.core.ResManager.fromResToString
import tv.purple.monolith.core.models.flag.Flag
import tv.purple.monolith.core.models.lifecycle.LifecycleAware
import tv.twitch.android.core.user.TwitchAccountManager
import javax.inject.Inject

class VodHunter @Inject constructor(
    private val nopRepository: NopRepository,
    private val manager: DonationManager
) : LifecycleAware {
    private val logger = LoggerWithTag("VODHunter")

    fun canUseVodHunter(): Boolean {
        return manager.isCurrentUserDonator()
    }

    fun hookVodManifestResponse(
        vodResponse: Single<Response<String>>,
        vodId: String
    ): Single<Response<String>> {
        if (!canUseVodHunter()) {
            return vodResponse
        }

        logger.debug("Try hooking response for vodId=$vodId")
        if (!Flag.VODHUNTER.asBoolean()) {
            logger.debug("VODHunter is disabled, returning original response")
            return vodResponse
        }

        return vodResponse.flatMap {
            when {
                it.isSuccessful -> {
                    logger.debug("VOD is available, no action needed")
                    return@flatMap vodResponse
                }

                it.code() == 403 -> {
                    logger.debug("VOD access denied (HTTP 403), attempting hooking...")
                    return@flatMap createSubFreePlaylist(
                        orgResponse = it,
                        vodId = vodId
                    )
                }

                else -> {
                    vodResponse
                }
            }
        }
    }

    private fun createSubFreePlaylist(
        orgResponse: Response<String>,
        vodId: String
    ): Single<Response<String>> {
        val payload = LibWrapper.getVodHunterPayload(stream = vodId)
        logger.debug("Generated payload --> $payload for vodId=$vodId")
        return nopRepository.getVodHunterPlaylist(payload = payload)
            .doOnSuccess {
                logger.info("OK")
                CoreUtil.showToast(
                    "[VODHunter] ${RES_STRINGS.purpletv_vodhunter_hunting.fromResToString()}"
                )
            }
            .onErrorResumeNext { th: Throwable ->
                logger.info("FAIL")
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

    override fun onAllComponentDestroyed() {}
    override fun onAllComponentStopped() {}
    override fun onAccountLogout() {}
    override fun onFirstActivityCreated() {
        manager.updateDonators()
    }

    override fun onFirstActivityStarted() {}
    override fun onConnectedToChannel(channelId: Int) {}
    override fun onConnectingToChannel(channelId: Int) {}
    override fun onAccountLogin(tam: TwitchAccountManager) {}
}