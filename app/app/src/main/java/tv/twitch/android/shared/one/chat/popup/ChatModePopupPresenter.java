package tv.twitch.android.shared.one.chat.popup;

import tv.purple.monolith.features.chat.bridge.ChatHook;
import tv.purple.monolith.models.exception.VirtualImpl;

public class ChatModePopupPresenter {
    /* ... */
    
    private final void maybeObserveChatModeChanges() {
        if (ChatHook.isHideOneChatPopupEnabled()) { // TODO: __INJECT_CODE
            return;
        }

        /* ... */

        throw new VirtualImpl();
    }

    /* ... */
}
