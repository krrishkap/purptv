package tv.purple.monolith.features.logs.component.data.model

import tv.twitch.android.shared.chat.pub.messages.ui.ChatHistoryMessage

data class MessageInfo(
    val userId: Int,
    val userName: String,
    val channelId: Int,
    val timestamp: Long,
    val msg: ChatHistoryMessage
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MessageInfo

        if (userId != other.userId) return false
        if (userName != other.userName) return false
        if (channelId != other.channelId) return false
        if (timestamp != other.timestamp) return false

        return true
    }

    override fun hashCode(): Int {
        var result = userId
        result = 31 * result + userName.hashCode()
        result = 31 * result + channelId
        result = 31 * result + timestamp.hashCode()

        return result
    }
}
