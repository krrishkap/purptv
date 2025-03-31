package tv.purple.monolith.component.api.data.api

import io.reactivex.Single
import retrofit2.http.GET
import tv.purple.monolith.models.retrofit.chatsen.Badges2Response

interface Chatsen2Api {
    @GET("/account/badges")
    fun getBadges(): Single<List<Badges2Response>>
}