package tv.twitch.android.shared.chat.messages.data;

import io.reactivex.Flowable;
import tv.purple.monolith.features.chat.bridge.ChatHook;
import tv.twitch.android.shared.chat.pub.messages.data.ChatMessage;

public class ChatMessagesProvider {
    /* ... */

    public final Flowable<ChatMessage> messagesObserver() {
        Flowable<ChatMessage> merge = Flowable.merge(Flowable.empty());
        merge = ChatHook.filterChatMessages(merge); // TODO: __INJECT_CODE
        return merge;
    }

    /* ... */
}
