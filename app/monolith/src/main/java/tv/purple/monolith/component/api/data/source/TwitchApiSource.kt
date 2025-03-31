package tv.purple.monolith.component.api.data.source

import io.reactivex.Scheduler
import io.reactivex.Single
import tv.purple.monolith.component.api.data.api.TwitchGQLApi
import tv.purple.monolith.component.api.data.mapper.TwitchApiMapper
import tv.purple.monolith.core.LoggerImpl
import tv.purple.monolith.models.data.UserInfo
import tv.purple.monolith.models.retrofit.gql.logs.DataResponse
import tv.purple.monolith.models.retrofit.gql.logs.ModLogsData
import tv.purple.monolith.models.retrofit.gql.logs.ModLogsRequest
import tv.purple.monolith.models.retrofit.gql.logs.UserInfoRequest
import tv.twitch.android.network.graphql.GraphQlService
import javax.inject.Inject

class TwitchApiSource @Inject constructor(
    private val twitchGQL: GraphQlService,
    private val twitchGQLApi: TwitchGQLApi,
    private val twitchApiMapper: TwitchApiMapper,
    private val scheduler: Scheduler
) {
    fun getUserInfo(login: String): Single<UserInfo> {
        return Single.never()
//        return twitchGQL.singleForQuery(
//            UserInfoQuery(
//                userLogin = Optional.presentIfNotNull(login),
//                userId = Optional.presentIfNotNull(null)
//            ), { data: UserInfoQuery.Data ->
//                return@singleForQuery twitchApiMapper.map(data) ?: throw Exception("data is null")
//            }, true, true, true, false
//        ).subscribeOn(scheduler)
    } // FIXME: rewrite to API

    fun getModLogs(username: String, channelId: String): Single<DataResponse<ModLogsData>> {
        return twitchGQLApi.requestUserInfo(
            UserInfoRequest(
                username = username
            )
        ).flatMap {
            LoggerImpl.debug { "userInfo --> ${it.data?.user}" }

            val userInfo = it.data?.user
                ?: return@flatMap Single.error(NullPointerException("userInfo is null"))
            val userId =
                userInfo.id ?: return@flatMap Single.error(NullPointerException("userId is null"))

            twitchGQLApi.requestModLogs(
                ModLogsRequest(
                    channelID = channelId,
                    senderID = userId
                )
            )
        }
    }
}