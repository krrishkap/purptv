package tv.purple.monolith.component.api.data.source

import io.reactivex.Scheduler
import io.reactivex.Single
import retrofit2.Response
import tv.purple.monolith.component.api.data.api.proxy.PerfprodApi
import tv.purple.monolith.component.api.di.scope.PP_AS
import tv.purple.monolith.component.api.di.scope.PP_EU
import tv.purple.monolith.component.api.di.scope.PP_EU2
import tv.purple.monolith.component.api.di.scope.PP_EU3
import tv.purple.monolith.component.api.di.scope.PP_EU4
import tv.purple.monolith.component.api.di.scope.PP_EU5
import tv.purple.monolith.component.api.di.scope.PP_NA
import tv.purple.monolith.component.api.di.scope.PP_SA
import javax.inject.Inject
import javax.inject.Named

class PerfprodDataSource @Inject constructor(
//    @Named(PP_EU)
//    private val euApi: PerfprodApi,
//    @Named(PP_EU2)
//    private val eu2Api: PerfprodApi,
//    @Named(PP_EU3)
//    private val eu3Api: PerfprodApi,
//    @Named(PP_EU4)
//    private val eu4Api: PerfprodApi,
//    @Named(PP_EU5)
//    private val eu5Api: PerfprodApi,
//    @Named(PP_NA)
//    private val naApi: PerfprodApi,
//    @Named(PP_AS)
//    private val asApi: PerfprodApi,
//    @Named(PP_SA)
//    private val saApi: PerfprodApi,
    private val scheduler: Scheduler
) {
    fun getEUPlaylist(channelName: String): Single<Response<String>> {
        return Single.never()
//        return euApi.getPlaylist(channelName)
//            .subscribeOn(scheduler)
    }

    fun getEU2Playlist(channelName: String): Single<Response<String>> {
        return Single.never()
//        return eu2Api.getPlaylist(channelName)
//            .subscribeOn(scheduler)
    }

    fun getEU3Playlist(channelName: String): Single<Response<String>> {
        return Single.never()
//        return eu3Api.getPlaylist(channelName)
//            .subscribeOn(scheduler)
    }

    fun getEU4Playlist(channelName: String): Single<Response<String>> {
        return Single.never()
//        return eu4Api.getPlaylist(channelName)
//            .subscribeOn(scheduler)
    }

    fun getEU5Playlist(channelName: String): Single<Response<String>> {
        return Single.never()
//        return eu5Api.getPlaylist(channelName)
//            .subscribeOn(scheduler)
    }

    fun getNAPlaylist(channelName: String): Single<Response<String>> {
        return Single.never()
//        return naApi.getPlaylist(channelName)
//            .subscribeOn(scheduler)
    }

    fun getSAPlaylist(channelName: String): Single<Response<String>> {
        return Single.never()
//        return saApi.getPlaylist(channelName)
//            .subscribeOn(scheduler)
    }

    fun getASPlaylist(channelName: String): Single<Response<String>> {
        return Single.never()
//        return asApi.getPlaylist(channelName)
//            .subscribeOn(scheduler)
    }
}