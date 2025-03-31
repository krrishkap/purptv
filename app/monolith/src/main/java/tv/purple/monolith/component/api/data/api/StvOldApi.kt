package tv.purple.monolith.component.api.data.api

import io.reactivex.Single
import retrofit2.http.GET
import tv.purple.monolith.models.retrofit.stv.BadgesData

interface StvOldApi {
    @GET("/v2/badges?user_identifier=twitch_id")
    fun badges(): Single<BadgesData>
}