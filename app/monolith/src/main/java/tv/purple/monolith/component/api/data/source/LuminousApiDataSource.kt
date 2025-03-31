package tv.purple.monolith.component.api.data.source

import io.reactivex.Scheduler
import io.reactivex.Single
import retrofit2.Response
import tv.purple.monolith.component.api.data.api.proxy.LuminousASApi
import tv.purple.monolith.component.api.data.api.proxy.LuminousEU2Api
import tv.purple.monolith.component.api.data.api.proxy.LuminousEUApi
import javax.inject.Inject

class LuminousApiDataSource @Inject constructor(
    private val euApi: LuminousEUApi,
    private val eu2Api: LuminousEU2Api,
    private val asApi: LuminousASApi,
    private val scheduler: Scheduler
) {
    fun getEUPlaylist(channelName: String): Single<Response<String>> {
        return euApi.getPlaylist(channelName)
            .subscribeOn(scheduler)
    }

    fun getEU2Playlist(channelName: String): Single<Response<String>> {
        return eu2Api.getPlaylist(channelName)
            .subscribeOn(scheduler)
    }

    fun getASPlaylist(channelName: String): Single<Response<String>> {
        return asApi.getPlaylist(channelName)
            .subscribeOn(scheduler)
    }
}