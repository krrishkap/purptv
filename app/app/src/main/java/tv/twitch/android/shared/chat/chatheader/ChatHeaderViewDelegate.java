package tv.twitch.android.shared.chat.chatheader;

import android.content.Context;
import android.widget.ImageView;

import tv.purple.monolith.features.ui.UI;
import tv.twitch.android.core.mvp.viewdelegate.RxViewDelegate;
import tv.twitch.android.core.mvp.viewdelegate.ViewDelegateState;

public class ChatHeaderViewDelegate extends RxViewDelegate<ViewDelegateState, ChatHeaderPresenter.Event.ViewEvent> {
    private final ImageView orangeHideKeyboard; // TODO: __INJECT_FILED

    public ChatHeaderViewDelegate(Context context, boolean z, boolean z2) {
        super(null);

        /* ... */

        orangeHideKeyboard = UI.getHideKeyboardButton(this); // TODO: __INJECT_CODE
    }

    public ImageView getOrangeHideKeyboard() { // TODO: __INJECT_CODE
        return orangeHideKeyboard;
    }
}