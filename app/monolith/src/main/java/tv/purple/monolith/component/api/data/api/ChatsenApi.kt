package tv.purple.monolith.component.api.data.api

import io.reactivex.Single
import retrofit2.http.GET
import tv.purple.monolith.models.retrofit.chatsen.BadgesResponse

interface ChatsenApi {
    @GET("/chatsen/resources/master/assets/data.json")
    fun getBadges(): Single<BadgesResponse>
}