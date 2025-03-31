package tv.purple.monolith.component.api.data.api

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import tv.purple.monolith.models.retrofit.stv.v3.EmoteSet
import tv.purple.monolith.models.retrofit.stv.v3.User

interface StvApi {
    @GET("/v3/emote-sets/global")
    fun globalEmotes(): Single<EmoteSet>

    @GET("/v3/users/twitch/{id}")
    fun user(@Path("id") userId: Int): Single<User>
}