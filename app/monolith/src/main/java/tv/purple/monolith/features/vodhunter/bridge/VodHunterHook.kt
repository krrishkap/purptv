package tv.purple.monolith.features.vodhunter.bridge

import io.reactivex.Single
import retrofit2.Response
import tv.purple.monolith.bridge.PurpleTVAppContainer
import tv.purple.monolith.core.models.flag.Flag

object VodHunterHook {
    @JvmStatic
    fun maybeHookVodManifestResponse(
        vodResponse: Single<Response<String>>,
        vodId: String
    ): Single<Response<String>> {
        return PurpleTVAppContainer.getInstance().provideVODHunter().hookVodManifestResponse(vodResponse, vodId)
    }

    @JvmStatic
    val isEnabled: Boolean
        get() = Flag.VODHUNTER.asBoolean()
}