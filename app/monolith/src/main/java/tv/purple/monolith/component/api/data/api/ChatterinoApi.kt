package tv.purple.monolith.component.api.data.api

import io.reactivex.Single
import retrofit2.http.GET
import tv.purple.monolith.models.retrofit.chatterino.BadgesData

interface ChatterinoApi {
    @GET("/badges")
    fun getBadges(): Single<BadgesData>
}