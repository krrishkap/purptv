package tv.purple.monolith.bridge.ex

import tv.twitch.android.core.strings.StringResource

interface IChatMessagesDispatcher {
    fun addSimpleMessage(message: String?)

    fun addSystemMessage(message: StringResource?, z: Boolean)

    // fun addChatHistoryMessage(message: ChatMessageInfo, channelId: Int) // FIXME: new_version
}