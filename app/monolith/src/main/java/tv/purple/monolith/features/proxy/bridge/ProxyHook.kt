package tv.purple.monolith.features.proxy.bridge

import io.reactivex.Single
import okhttp3.OkHttpClient
import retrofit2.Response
import tv.purple.monolith.features.proxy.Proxy
import tv.twitch.android.models.manifest.extm3u

object ProxyHook {
    @JvmStatic
    fun tryHookStreamManifestResponse(
        streamName: String,
        orgStreamManifest: Single<Response<String>>
    ): Single<Response<String>> {
        return Proxy.get().maybeStreamManifestResponse(streamName, orgStreamManifest)
    }

    @JvmStatic
    fun tryHookPlaylistUrl(requestUrl: String, model: extm3u): String {
        if (model.ProxyUrl.isNullOrBlank()) {
            return requestUrl
        }

        return model.ProxyUrl
    }

    @JvmStatic
    fun maybeAddInterceptor(builder: OkHttpClient.Builder) {
        builder.addNetworkInterceptor(LolTvApiInterceptor())
        builder.addNetworkInterceptor(GayPlusApiInterceptor())
        builder.addNetworkInterceptor(GayPlus2ApiInterceptor())
    }
}