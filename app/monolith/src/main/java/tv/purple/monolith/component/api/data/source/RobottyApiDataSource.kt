package tv.purple.monolith.component.api.data.source

import io.reactivex.Scheduler
import io.reactivex.Single
import tv.purple.monolith.component.api.data.api.RobottyApi
import tv.purple.monolith.models.retrofit.robotty.RobottyRecentMessagesResponse
import javax.inject.Inject

class RobottyApiDataSource @Inject constructor(
    private val api: RobottyApi,
    private val scheduler: Scheduler
) {
    fun getRecentMessages(channelLogin: String): Single<RobottyRecentMessagesResponse> {
        return api.recentMessages(channelLogin)
            .subscribeOn(scheduler)
    }
}