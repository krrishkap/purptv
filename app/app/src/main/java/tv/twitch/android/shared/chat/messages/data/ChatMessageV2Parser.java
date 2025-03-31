package tv.twitch.android.shared.chat.messages.data;

import kotlin.NotImplementedError;

import tv.purple.monolith.features.chat.bridge.ChatHook;
import tv.twitch.chat.library.model.ChatMessageInfo;
import tv.twitch.android.shared.chat.pub.messages.data.ChatMessage;

public class ChatMessageV2Parser {
    /* ... */

    public final ChatMessage.LiveChatMessage convertChatMessage(int i10, LiveChatMessageData data, String str, ChatMessageInfo messageInfo) {
        ChatMessage.LiveChatMessage msg = null;

        msg = ChatHook.hookLiveChatMessage(msg, i10); // TODO: __INJECT_CODE

        String a = msg.toString();

        throw new NotImplementedError();
    }

    /* ... */
}
