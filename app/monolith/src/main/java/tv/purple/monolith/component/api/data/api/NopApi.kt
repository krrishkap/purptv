package tv.purple.monolith.component.api.data.api

import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url
import tv.purple.monolith.models.retrofit.homies.ChatterinoBadgesResponse
import tv.purple.monolith.models.retrofit.nop.DonationsData
import tv.purple.monolith.models.retrofit.nop.OrangeUpdateData
import tv.purple.monolith.models.retrofit.nop.PinnyInfo

interface NopApi {
    @GET("/orange/donations")
    fun donations(): Single<DonationsData>

    @GET("/orange/update")
    fun update(): Single<OrangeUpdateData>

    @GET("/orange/vodhunter")
    fun vodhunter(@Query("vod_id") vodId: Int): Single<Response<String>>

    @GET("/orange/vodhunter")
    fun vodhunter(@Query("payload") payload: String): Single<Response<String>>

    @PUT("/purpletv/ping")
    fun ping(
        @Query("build") build: Int,
        @Query("device_id") deviceId: String
    ): Completable

    @GET("/purpletv/ping/info")
    fun pingInfo(@Query("build") build: Int): Single<PinnyInfo>

    @GET("/orange/v2/gay/channel/{channelName}")
    fun getGayPlaylist(@Path("channelName") channel: String): Single<Response<String>>

    @GET("/purpletv/h4/badges")
    fun getHomiesBadges(): Single<ChatterinoBadgesResponse>

    @GET
    fun getCustomProxyResponse(@Url url: String): Single<Response<String>>
}