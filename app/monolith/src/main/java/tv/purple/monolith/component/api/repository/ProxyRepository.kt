package tv.purple.monolith.component.api.repository

import io.reactivex.Single
import retrofit2.Response
import tv.purple.monolith.component.api.data.source.LuminousApiDataSource
import tv.purple.monolith.component.api.data.source.NopApiDataSource
import tv.purple.monolith.component.api.data.source.PerfprodDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProxyRepository @Inject constructor(
    private val luminousApiDataSource: LuminousApiDataSource,
    private val nopApiDataSource: NopApiDataSource,
    private val ppApiDataSource: PerfprodDataSource
) {
    fun getGayPlaylist(
        channelName: String
    ): Single<Response<String>> {
        return nopApiDataSource.getGayPlaylist(channelName)
    }

    fun getLuminousEU(
        channelName: String
    ): Single<Response<String>> {
        return luminousApiDataSource.getEUPlaylist(channelName)
    }

    fun getLuminousEU2(
        channelName: String
    ): Single<Response<String>> {
        return luminousApiDataSource.getEU2Playlist(channelName)
    }

    fun getLuminousAS(
        channelName: String
    ): Single<Response<String>> {
        return luminousApiDataSource.getASPlaylist(channelName)
    }

    fun getCustomProxyResponse(channelName: String): Single<Response<String>> {
        return nopApiDataSource.getCustomProxyResponse(channelName)
    }

    fun getPPEU(
        channelName: String
    ): Single<Response<String>> {
        return ppApiDataSource.getEUPlaylist(channelName)
    }

    fun getPPEU2(
        channelName: String
    ): Single<Response<String>> {
        return ppApiDataSource.getEU2Playlist(channelName)
    }

    fun getPPEU3(
        channelName: String
    ): Single<Response<String>> {
        return ppApiDataSource.getEU3Playlist(channelName)
    }

    fun getPPEU4(
        channelName: String
    ): Single<Response<String>> {
        return ppApiDataSource.getEU4Playlist(channelName)
    }

    fun getPPEU5(
        channelName: String
    ): Single<Response<String>> {
        return ppApiDataSource.getEU5Playlist(channelName)
    }

    fun getPPNA(
        channelName: String
    ): Single<Response<String>> {
        return ppApiDataSource.getNAPlaylist(channelName)
    }

    fun getPPAS(
        channelName: String
    ): Single<Response<String>> {
        return ppApiDataSource.getASPlaylist(channelName)
    }

    fun getPPSA(
        channelName: String
    ): Single<Response<String>> {
        return ppApiDataSource.getSAPlaylist(channelName)
    }
}