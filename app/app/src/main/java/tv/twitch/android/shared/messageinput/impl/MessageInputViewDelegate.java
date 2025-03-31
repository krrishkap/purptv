package tv.twitch.android.shared.messageinput.impl;

import tv.purple.monolith.features.chat.ChatHookProvider;
import tv.purple.monolith.features.chat.bridge.ChatHook;
import tv.purple.monolith.models.exception.VirtualImpl;
import tv.twitch.android.core.mvp.viewdelegate.ViewDelegateState;

public class MessageInputViewDelegate {
    /* ... */

    public void render(State state) {
        /* ... */

        // state.isBitsPickerButtonVisible()
        boolean b = false;
        b = ChatHook.changeBitsButtonVisibility(b);  // TODO: __HOOK_PARAM

        /* ... */

        throw new VirtualImpl();
    }

    /* ... */

    public static final class State implements ViewDelegateState {
        /* ... */
    }
}