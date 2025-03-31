package tv.purple.monolith.features.logs.component.data.model

import tv.twitch.android.shared.chat.pub.messages.data.ChatMessage.LiveChatMessage
import java.util.*

interface MessageItem {
    data class Content(val content: LiveChatMessage) : MessageItem
    data class Header(val date: Date) : MessageItem
}