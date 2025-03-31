package tv.purple.monolith.component.api.repository

import io.reactivex.Single
import tv.purple.monolith.component.api.data.source.RobottyApiDataSource
import tv.purple.monolith.models.retrofit.robotty.RobottyRecentMessagesResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RobottyRepository @Inject constructor(
    private val robottyApiDataSource: RobottyApiDataSource
) {
    fun getRecentMessages(channelLogin: String): Single<RobottyRecentMessagesResponse> {
        return robottyApiDataSource.getRecentMessages(channelLogin)
    }
}