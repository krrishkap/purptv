package tv.purple.monolith.component.api.data.api

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import tv.purple.monolith.models.retrofit.robotty.RobottyRecentMessagesResponse

interface RobottyApi {
    @GET("/api/v2/recent-messages/{channel_login}")
    fun recentMessages(
        @Path("channel_login") channelLogin: String,
        @Query("limit") limit: Int = 100
    ): Single<RobottyRecentMessagesResponse>
}