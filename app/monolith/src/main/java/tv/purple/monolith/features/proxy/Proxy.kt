package tv.purple.monolith.features.proxy

import androidx.fragment.app.Fragment
import com.google.android.exoplayer2.upstream.DataSpec
import io.reactivex.Single
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Response
import tv.purple.monolith.bridge.PurpleTVAppContainer.Companion.getInstance
import tv.purple.monolith.bridge.RES_STRINGS
import tv.purple.monolith.component.api.repository.ProxyRepository
import tv.purple.monolith.core.CoreUtil
import tv.purple.monolith.core.LoggerWithTag
import tv.purple.monolith.core.ResManager.fromResToString
import tv.purple.monolith.core.models.flag.Flag
import tv.purple.monolith.core.models.flag.variants.ProxyImpl
import tv.purple.monolith.features.proxy.view.CustomProxyUrlFragment
import java.util.Collections
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Proxy @Inject constructor(
    private val repository: ProxyRepository
) {
    private val logger = LoggerWithTag("Proxy")

    fun maybeStreamManifestResponse(
        channelName: String,
        manifest: Single<Response<String>>
    ): Single<Response<String>> {
        val variant = Flag.PROXY_LIST.asVariant<ProxyImpl>()
        if (variant == ProxyImpl.Disabled) {
            return manifest
        }

        return when (variant) {
            ProxyImpl.GAY -> createGayProxySingleResponse(
                twitchResponse = manifest,
                channelName = channelName
            )
            ProxyImpl.LUMINOUS_EU -> createLuminousEUProxySingleResponse(
                twitchResponse = manifest,
                channelName = channelName
            )
            ProxyImpl.LUMINOUS_EU2 -> createLuminousEU2ProxySingleResponse(
                twitchResponse = manifest,
                channelName = channelName
            )
            ProxyImpl.LUMINOUS_AS -> createLuminousASProxySingleResponse(
                twitchResponse = manifest,
                channelName = channelName
            )
            ProxyImpl.CUSTOM -> createCustomProxySingleResponse(
                twitchResponse = manifest,
                channelName = channelName
            )
            ProxyImpl.PP_EU -> createPPEUProxySingleResponse(
                twitchResponse = manifest,
                channelName = channelName
            )
            ProxyImpl.PP_EU2 -> createPPEU2ProxySingleResponse(
                twitchResponse = manifest,
                channelName = channelName
            )
            ProxyImpl.PP_EU3 -> createPPEU3ProxySingleResponse(
                twitchResponse = manifest,
                channelName = channelName
            )
            ProxyImpl.PP_EU4 -> createPPEU4ProxySingleResponse(
                twitchResponse = manifest,
                channelName = channelName
            )
            ProxyImpl.PP_EU5 -> createPPEU5ProxySingleResponse(
                twitchResponse = manifest,
                channelName = channelName
            )
            ProxyImpl.PP_NA -> createPPNAProxySingleResponse(
                twitchResponse = manifest,
                channelName = channelName
            )
            ProxyImpl.PP_SA -> createPPSAProxySingleResponse(
                twitchResponse = manifest,
                channelName = channelName
            )
            ProxyImpl.PP_AS -> createPPASProxySingleResponse(
                twitchResponse = manifest,
                channelName = channelName
            )
            else -> manifest
        }
    }

    companion object {
        @JvmStatic
        fun get(): Proxy {
            return getInstance().provideProxy()
        }

        private fun createPlaylistResponse(
            playlist: String, orgResponse: Response<String>
        ): Response<String> {
            val patchedResponse = orgResponse.raw().newBuilder().apply {
                body(playlist.toResponseBody("application/vnd.apple.mpegurl".toMediaType()))
            }.build()

            return Response.success(playlist, patchedResponse)
        }

        private fun getRequestTime(response: okhttp3.Response): Int = try {
            val tx = response.sentRequestAtMillis
            val rx = response.receivedResponseAtMillis
            (rx - tx).toInt()
        } catch (th: Throwable) {
            0
        }

        @JvmStatic
        fun patchExoPlayerDataSpec(dataSpec: DataSpec?): DataSpec? {
            dataSpec ?: run {
                return null
            }

            val url = dataSpec.uri.toString()
            val headers = when {
                url.contains("https://api.ttv.lol/") -> Collections.unmodifiableMap(
                    dataSpec.httpRequestHeaders.toMutableMap().apply {
                        put("x-donate-to", "https://ttv.lol/donate")
                    }
                )

                url.contains(".ttvnw.net/v1/playlist/") -> mapOf(
                    "Accept" to "application/x-mpegURL, application/vnd.apple.mpegurl, application/json, text/plain"
                )

                else -> null
            } ?: return dataSpec

            return DataSpec(
                dataSpec.uri,
                dataSpec.httpMethod,
                dataSpec.httpBody,
                dataSpec.absoluteStreamPosition,
                dataSpec.position,
                dataSpec.length,
                dataSpec.key,
                dataSpec.flags,
                headers
            )
        }
    }

    private fun trySwapPlaylist(
        twitchResponse: Single<Response<String>>,
        proxyResponse: Single<Response<String>>,
        proxyName: String
    ): Single<Response<String>> {
        logger.debug("[$proxyName] Start request...")
        return proxyResponse.flatMap { proxyPlaylist ->
            if (!proxyPlaylist.isSuccessful) {
                CoreUtil.showToast(
                    RES_STRINGS.purpletv_generic_error_d.fromResToString(
                        "Proxy",
                        RES_STRINGS.purpletv_proxy_error_ur.fromResToString()
                    )
                )
                logger.debug("[$proxyName] Unsuccessful")
                return@flatMap Single.error(Exception("proxy_unsuccessfull"))
            }
            val time = getRequestTime(proxyPlaylist.raw())
            logger.debug("[$proxyName] Time: $time")
            var body = proxyPlaylist.body() ?: run {
                CoreUtil.showToast(
                    RES_STRINGS.purpletv_generic_error_d.fromResToString(
                        "Proxy",
                        RES_STRINGS.purpletv_proxy_error_cpr.fromResToString()
                    )
                )
                logger.debug("[$proxyName] Body is null")
                return@flatMap Single.error(Exception("proxy_error"))
            }
            val proxyUrl = proxyPlaylist.raw().request.url
            body = body.replace(
                "#EXT-X-TWITCH-INFO:",
                "#EXT-X-TWITCH-INFO:PROXY-SERVER=\"$proxyName ($time ms)\",PROXY-URL=\"$proxyUrl\","
            )
            logger.debug("[$proxyName] Result: $body")
            Single.just(createPlaylistResponse(body, proxyPlaylist))
        }.onErrorResumeNext { th: Throwable ->
            CoreUtil.showToast(
                RES_STRINGS.purpletv_generic_error_d.fromResToString(
                    "Proxy",
                    th.localizedMessage ?: "<empty>"
                )
            )
            logger.debug("[$proxyName] Ex: ${th.localizedMessage}")
            th.printStackTrace()
            twitchResponse
        }
    }

    private fun createGayProxySingleResponse(
        twitchResponse: Single<Response<String>>,
        channelName: String
    ): Single<Response<String>> {
        return trySwapPlaylist(
            twitchResponse = twitchResponse,
            proxyResponse = repository.getGayPlaylist(channelName = channelName),
            proxyName = "GAY"
        )
    }

    private fun createLuminousEUProxySingleResponse(
        twitchResponse: Single<Response<String>>,
        channelName: String
    ): Single<Response<String>> {
        return trySwapPlaylist(
            twitchResponse = twitchResponse,
            proxyResponse = repository.getLuminousEU(channelName = channelName),
            proxyName = "Luminous (EU)"
        )
    }

    private fun createLuminousEU2ProxySingleResponse(
        twitchResponse: Single<Response<String>>,
        channelName: String
    ): Single<Response<String>> {
        return trySwapPlaylist(
            twitchResponse = twitchResponse,
            proxyResponse = repository.getLuminousEU2(channelName = channelName),
            proxyName = "Luminous (EU2)"
        )
    }

    private fun createCustomProxySingleResponse(
        twitchResponse: Single<Response<String>>,
        channelName: String
    ): Single<Response<String>> {
        return trySwapPlaylist(
            twitchResponse = twitchResponse,
            proxyResponse = repository.getCustomProxyResponse(channelName = channelName),
            proxyName = "Custom"
        )
    }

    private fun createLuminousASProxySingleResponse(
        twitchResponse: Single<Response<String>>,
        channelName: String
    ): Single<Response<String>> {
        return trySwapPlaylist(
            twitchResponse = twitchResponse,
            proxyResponse = repository.getLuminousAS(channelName = channelName),
            proxyName = "Luminous (AS)"
        )
    }

    private fun createPPEUProxySingleResponse(
        twitchResponse: Single<Response<String>>,
        channelName: String
    ): Single<Response<String>> {
        return trySwapPlaylist(
            twitchResponse = twitchResponse,
            proxyResponse = repository.getPPEU(channelName = channelName),
            proxyName = "PerfProd (EU)"
        )
    }

    private fun createPPEU2ProxySingleResponse(
        twitchResponse: Single<Response<String>>,
        channelName: String
    ): Single<Response<String>> {
        return trySwapPlaylist(
            twitchResponse = twitchResponse,
            proxyResponse = repository.getPPEU2(channelName = channelName),
            proxyName = "PerfProd (EU2)"
        )
    }

    private fun createPPEU3ProxySingleResponse(
        twitchResponse: Single<Response<String>>,
        channelName: String
    ): Single<Response<String>> {
        return trySwapPlaylist(
            twitchResponse = twitchResponse,
            proxyResponse = repository.getPPEU3(channelName = channelName),
            proxyName = "PerfProd (EU3)"
        )
    }

    private fun createPPEU4ProxySingleResponse(
        twitchResponse: Single<Response<String>>,
        channelName: String
    ): Single<Response<String>> {
        return trySwapPlaylist(
            twitchResponse = twitchResponse,
            proxyResponse = repository.getPPEU4(channelName = channelName),
            proxyName = "PerfProd (EU4)"
        )
    }

    private fun createPPEU5ProxySingleResponse(
        twitchResponse: Single<Response<String>>,
        channelName: String
    ): Single<Response<String>> {
        return trySwapPlaylist(
            twitchResponse = twitchResponse,
            proxyResponse = repository.getPPEU5(channelName = channelName),
            proxyName = "PerfProd (EU5)"
        )
    }

    private fun createPPNAProxySingleResponse(
        twitchResponse: Single<Response<String>>,
        channelName: String
    ): Single<Response<String>> {
        return trySwapPlaylist(
            twitchResponse = twitchResponse,
            proxyResponse = repository.getPPNA(channelName = channelName),
            proxyName = "PerfProd (NA)"
        )
    }

    private fun createPPASProxySingleResponse(
        twitchResponse: Single<Response<String>>,
        channelName: String
    ): Single<Response<String>> {
        return trySwapPlaylist(
            twitchResponse = twitchResponse,
            proxyResponse = repository.getPPAS(channelName = channelName),
            proxyName = "PerfProd (AS)"
        )
    }

    private fun createPPSAProxySingleResponse(
        twitchResponse: Single<Response<String>>,
        channelName: String
    ): Single<Response<String>> {
        return trySwapPlaylist(
            twitchResponse = twitchResponse,
            proxyResponse = repository.getPPSA(channelName = channelName),
            proxyName = "PerfProd (SA)"
        )
    }

    fun createCustomUrlFragment(): Fragment {
        return CustomProxyUrlFragment()
    }
}