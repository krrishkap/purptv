package tv.twitch.android.shared.chat;

import android.view.LayoutInflater;
import android.view.View;

import androidx.fragment.app.FragmentActivity;

import tv.purple.monolith.features.ui.UI;
import tv.twitch.android.core.mvp.viewdelegate.ViewDelegateContainer;

public class ChatViewDelegate {
    private ViewDelegateContainer chatHeaderContainer;

    /* ... */

    private ChatViewDelegate(FragmentActivity fragmentActivity, View view, boolean z, boolean z2, Object iChatListView, LayoutInflater layoutInflater) {
        /* ... */

        UI.maybeChangeBackgroundColor(chatHeaderContainer.getViewGroup()); // TODO: __INJECT_CODE
    }

    /* ... */
}