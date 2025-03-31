package tv.purple.monolith.component.api.data.api.proxy

import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface LuminousEU2Api {
    @GET("/playlist/{channelName}")
    fun getPlaylist(
        @Path("channelName") channel: String,
        @Query("allow_source") allowSource: Boolean = true,
        @Query("allow_audio_only") allowAudioOnly: Boolean = true,
        @Query("allow_spectre") allowSpectre: Boolean = false,
        @Query("fast_bread") fastBread: Boolean = true,
        @Query("type") type: String = "any",
        @Query("player") player: String = "twitchweb"
    ): Single<Response<String>>
}