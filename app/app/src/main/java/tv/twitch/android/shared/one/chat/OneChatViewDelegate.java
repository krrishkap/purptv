package tv.twitch.android.shared.one.chat;

import android.view.View;

import java.util.List;

import tv.purple.monolith.features.chat.bridge.ChatHook;
import tv.twitch.android.core.mvp.viewdelegate.ViewDelegateState;
import tv.twitch.android.shared.one.chat.databinding.OneChatLayoutBinding;

public class OneChatViewDelegate {
    private List<View> defaultRightSideViews;
    private OneChatLayoutBinding binding;

    /* ... */

    public void render(State state) {
        /* ... */

        ChatHook.maybeHideOneChatRightSideViews(defaultRightSideViews, binding.bitsButton); // TODO: __INJECT_CODE
    }

    /* ... */

    public static final class State implements ViewDelegateState {
        /* ... */
    }
}