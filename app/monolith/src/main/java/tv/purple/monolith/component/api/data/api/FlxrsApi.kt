package tv.purple.monolith.component.api.data.api

import io.reactivex.Single
import retrofit2.http.GET
import tv.purple.monolith.models.retrofit.flxrs.DankChatBadgeResponse

interface FlxrsApi {
    @GET("/api/badges")
    fun badges(): Single<List<DankChatBadgeResponse>>
}
