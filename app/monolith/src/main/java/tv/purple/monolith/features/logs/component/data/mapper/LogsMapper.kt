package tv.purple.monolith.features.logs.component.data.mapper

import android.graphics.Color
import tv.purple.monolith.core.util.DateUtil
import tv.purple.monolith.features.logs.component.data.model.MessageItem
import tv.purple.monolith.models.retrofit.gql.logs.Content
import tv.purple.monolith.models.retrofit.gql.logs.DataResponse
import tv.purple.monolith.models.retrofit.gql.logs.ModLogsData
import tv.purple.monolith.models.retrofit.gql.logs.Sender
import tv.twitch.android.models.chat.MessageBadge
import tv.twitch.android.shared.chat.messages.data.ChatMessageV2Parser
import tv.twitch.android.shared.chat.pub.messages.data.AutoModMessageFlags
import tv.twitch.android.shared.chat.pub.messages.data.ChatMessage.LiveChatMessage
import tv.twitch.android.shared.chat.pub.messages.data.MessageToken
import tv.twitch.android.shared.chat.pub.messages.ui.ChatMessageInterface
import tv.twitch.android.shared.chat.pub.messages.ui.ChatMessageType
import java.util.Date
import javax.inject.Inject

class LogsMapper @Inject constructor(
) {
    fun mapTwitchLogs(
        parser: ChatMessageV2Parser,
        response: DataResponse<ModLogsData>,
        channelId: Int
    ): List<MessageItem> {
        return convert(response.data?.logs?.messages?.edges?.mapNotNull { edge ->
            edge.node?.let { node ->
                mapTwitchLogs(
                    sender = node.sender,
                    content = node.content,
                    sentAt = node.sentAt,
                    channelId = channelId,
                    parser = parser
                )
            }
        })
    }

    private fun convert(messages: List<LiveChatMessage>?): List<MessageItem> {
        var currentDate: Date? = null
        val stack = mutableListOf<MessageItem>()
        messages?.forEach { message ->
            if (!DateUtil.isSameDate(currentDate, Date(message.timestampSeconds))) {
                stack.add(MessageItem.Header(date = Date(message.timestampSeconds)))
                currentDate = Date(message.timestampSeconds)
            }
            stack.add(MessageItem.Content(content = message))
        }

        return stack
    }

    private fun mapTwitchLogs(
        sender: Sender?,
        content: Content?,
        sentAt: String?,
        channelId: Int,
        parser: ChatMessageV2Parser
    ): LiveChatMessage? {
        sender ?: return null
        content ?: return null

        val tokens = mutableListOf<MessageToken>()

        content.fragments?.forEach { tokenFragment ->
            val fragmentContent = tokenFragment.content
            val token = if (fragmentContent != null) {
                if (fragmentContent.__typename == "Emote") {
                    MessageToken.EmoticonToken(fragmentContent.token, fragmentContent.emoteID)
                } else {
                    MessageToken.TextToken(
                        tokenFragment.text,
                        AutoModMessageFlags()
                    )
                }
            } else {
                MessageToken.TextToken(
                    tokenFragment.text,
                    AutoModMessageFlags()
                )
            }
            tokens.add(token)
        }

        val badges = mutableListOf<MessageBadge>()
        sender.displayBadges?.forEach { displayBadge ->
            displayBadge.setID.let { setID ->
                displayBadge.version.let { version ->
                    badges.add(MessageBadge(setID, version))
                }
            }
        }

        val date = DateUtil.getStandardizeDateString(sentAt.toString()) ?: Date()

        val cmi = object : ChatMessageInterface {
            override fun getBadges(): MutableList<MessageBadge> = badges

            override fun getChatMessageTags(): MutableMap<String, String> = mutableMapOf()

            override fun getDisplayName(): String = sender.displayName ?: "<DEBUG>"

            override fun getMessageType(): ChatMessageType = ChatMessageType.None.INSTANCE

            override fun getTimestampSeconds(): Long = date.time

            override fun getTokens(): MutableList<MessageToken> = tokens

            override fun getUserId(): Int = sender.id?.toIntOrNull() ?: 0

            override fun getUserName(): String = sender.login ?: "<DEBUG>"

            override fun isAction(): Boolean = false

            override fun isDeleted(): Boolean = false
        }
        return parser.convertChatMessageInterface(
            channelId,
            cmi,
            Color.parseColor(sender.chatColor),
            true
        )
    }

//    fun mapLocalLogs(messages: List<MessageInfo>): List<MessageItem> {
//        return convert(messages.map {
//            ChatMessage(
//                token = it.msg,
//                timestamp = DateUtil.getStandardizeDateString(it.timestamp.toString()) ?: Date(),
//                channelId = it.channelId
//            )
//        })
//    }
}