package tv.twitch.android.shared.chat.chatheader;

import tv.purple.monolith.features.chat.bridge.ChatHook;

public class ChatHeaderVisibilityCalculator {
    /* ... */

    private final boolean shouldHideChatHeaderBasedOnDefaultRules(boolean p0) { // TODO: __REPLACE_METHOD
        if (ChatHook.getHideChatHeader()) {
            return true;
        }

        return p0;
    }

    /* ... */
}
