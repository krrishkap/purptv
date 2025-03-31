package tv.purple.monolith.component.api.data.api

import io.reactivex.Single
import retrofit2.http.GET
import tv.purple.monolith.models.retrofit.homies.BadgesResponse
import tv.purple.monolith.models.retrofit.homies.EmotesResponse

interface HomiesApi {
    @GET("/emotes")
    fun getEmotes(): Single<EmotesResponse>

    @GET("/badges")
    fun getBadges(): Single<BadgesResponse>

    @GET("/badges2")
    fun getBadges2(): Single<BadgesResponse>
}