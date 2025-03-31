package tv.purple.monolith.features.chat.bridge

import tv.twitch.android.models.chat.MessageBadge
import tv.twitch.android.shared.chat.pub.messages.data.MessageToken
import tv.twitch.android.shared.chat.pub.messages.ui.ChatMessageInterface
import tv.twitch.android.shared.chat.pub.messages.ui.ChatMessageType

class ChatMessageInterfaceWrapper(
    private val cmi: ChatMessageInterface,
    private val badges: MutableList<MessageBadge> = cmi.badges,
    private val tokens: MutableList<MessageToken> = cmi.tokens,
    private val displayName: String? = cmi.displayName
) : ChatMessageInterface {
    override fun getBadges(): MutableList<MessageBadge> {
        return badges
    }

    override fun getChatMessageTags(): MutableMap<String, String> {
        return cmi.chatMessageTags
    }

    override fun getDisplayName(): String? {
        return displayName
    }

    override fun getMessageType(): ChatMessageType {
        return cmi.messageType
    }

    override fun getTimestampSeconds(): Long {
        return cmi.timestampSeconds
    }

    override fun getTokens(): MutableList<MessageToken> {
        return tokens
    }

    override fun getUserId(): Int {
        return cmi.userId
    }

    override fun getUserName(): String? {
        return cmi.userName
    }

    override fun isAction(): Boolean {
        return cmi.isAction
    }

    override fun isDeleted(): Boolean {
        return cmi.isDeleted
    }
}