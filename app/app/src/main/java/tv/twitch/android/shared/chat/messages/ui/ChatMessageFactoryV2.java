package tv.twitch.android.shared.chat.messages.ui;

import android.content.Context;

import tv.purple.monolith.features.chat.bridge.ChatHook;
import tv.purple.monolith.models.exception.VirtualImpl;
import tv.twitch.android.core.mvp.viewdelegate.EventDispatcher;
import tv.twitch.android.core.strings.StringResource;
import tv.twitch.android.shared.chat.pub.messages.data.ChatMessage;
import tv.twitch.android.shared.chat.pub.messages.ui.ChatItemClickEvent;
import tv.twitch.android.shared.chat.pub.messages.ui.ChatMessageClickedEvents;
import tv.twitch.android.shared.chat.pub.messages.ui.ChatMessageUrlOptions;

public class ChatMessageFactoryV2 {
    /* ... */

    public final MessageRecyclerItem createChatMessageItem(Context context, StringResource stringResource, final ChatMessage.LiveChatMessage chatMessage, final boolean z10, final ChatMessageUrlOptions options, final boolean z11, final EventDispatcher<ChatItemClickEvent> chatItemClickEventDispatcher, EventDispatcher<ChatMessageClickedEvents> eventDispatcher) {
        MessageRecyclerItem ret = null;
        ChatHook.setShouldHighlightBackground(ret, chatMessage);

        throw new VirtualImpl();
    }

    /* ... */
}
