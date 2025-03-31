package tv.purple.monolith.features.logs.component.data.model

import tv.twitch.android.shared.chat.pub.messages.ui.ChatHistoryMessage
import java.util.Date

data class ChatMessage(
    val token: ChatHistoryMessage,
    val timestamp: Date,
    val channelId: Int
)