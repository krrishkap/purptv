package tv.purple.monolith.component.api.data.api

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import tv.purple.monolith.models.retrofit.bttv.BttvBadge
import tv.purple.monolith.models.retrofit.bttv.BttvChannelData
import tv.purple.monolith.models.retrofit.bttv.BttvEmote
import tv.purple.monolith.models.retrofit.bttv.FfzEmote

interface BttvApi {
    @GET("/3/cached/emotes/global")
    fun globalBttvEmotes(): Single<List<BttvEmote>>

    @GET("/3/cached/frankerfacez/emotes/global")
    fun globalFfzEmotes(): Single<List<FfzEmote>>

    @GET("/3/cached/users/twitch/{id}")
    fun bttvEmotes(@Path("id") channelId: Int): Single<BttvChannelData>

    @GET("/3/cached/frankerfacez/users/twitch/{id}")
    fun ffzEmotes(@Path("id") channelId: Int): Single<List<FfzEmote>>

    @GET("/3/cached/badges")
    fun badges(): Single<List<BttvBadge>>
}