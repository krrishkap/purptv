package tv.twitch.android.shared.messageinput.impl;

import android.view.View;
import android.widget.ImageView;

import tv.purple.monolith.features.ui.UI;
import tv.purple.monolith.features.ui.bridge.UIHook;
import tv.purple.monolith.models.exception.VirtualImpl;
import tv.twitch.android.core.mvp.viewdelegate.BaseViewDelegate;
import tv.twitch.android.shared.messageinput.pub.PrimaryButtonAction;

public class ChatMessageInputViewDelegate extends BaseViewDelegate {
    private final ImageView openChatSettingsButton = null;
    private final View inputBackground = null;

    /* ... */

    public ChatMessageInputViewDelegate(View view) {
        super(view);

        /* ... */

        UIHook.maybeHideChatSettingsButton(openChatSettingsButton); // TODO: __INJECT_CODE
        UI.maybeChangeBackgroundColor(inputBackground); // TODO: __INJECT_CODE

        throw new VirtualImpl();
    }

    private final void setPrimaryButton(PrimaryButtonAction var1) {
        UIHook.maybeHideChatSettingsButton(openChatSettingsButton); // TODO: __INJECT_CODE

        /* ... */

        throw new VirtualImpl();
    }

    /* ... */

    @Override
    public void show() { // TODO: __INJECT_METHOD
        if (UIHook.shouldHideMessageInput(this)) {
            super.hide();
        } else {
            super.show();
        }
    }

    /* ... */
}
