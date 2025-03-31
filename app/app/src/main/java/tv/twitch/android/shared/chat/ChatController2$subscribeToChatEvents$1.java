package tv.twitch.android.shared.chat;

import tv.purple.monolith.features.logs.ChatLogs;
import tv.twitch.chat.library.ChatEvent;

public class ChatController2$subscribeToChatEvents$1 {
    /* ... */

    public final void invoke(ChatEvent chatEvent) {
        /* ... */

        ChatChannelEvent access$toEvent = null;

        /* ... */

        ChatLogs.maybeAddToLocalLogs(access$toEvent); // TODO: __INJECT_CODE

        /* ... */
    }

    /* ... */
}
