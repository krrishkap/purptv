package tv.purple.monolith.features.logs.component.data.source

import io.reactivex.Scheduler
import io.reactivex.Single
import tv.purple.monolith.component.api.repository.TwitchRepository
import tv.purple.monolith.features.logs.component.data.mapper.LogsMapper
import tv.purple.monolith.features.logs.component.data.model.MessageItem
import tv.twitch.android.shared.chat.messages.data.ChatMessageV2Parser
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TwitchLogsDataSource @Inject constructor(
    private val twitchRepository: TwitchRepository,
    private val mapper: LogsMapper,
    private val scheduler: Scheduler
) {
    fun getMessages(
        parser: ChatMessageV2Parser,
        username: String,
        channelId: String
    ): Single<List<MessageItem>> {
        return twitchRepository.getModLogs(
            channelId = channelId,
            username = username
        ).map { response ->
            mapper.mapTwitchLogs(
                parser = parser,
                response = response,
                channelId = channelId.toIntOrNull() ?: 0
            )
        }.subscribeOn(scheduler)
    }
}