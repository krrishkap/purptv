package tv.twitch.android.shared.messageinput.impl.chatrestrictions;

import android.view.View;

import tv.purple.monolith.features.ui.UI;
import tv.twitch.android.core.mvp.viewdelegate.RxViewDelegate;

public class ChatRestrictionsBannerViewDelegate extends RxViewDelegate {
    /* ... */

    public ChatRestrictionsBannerViewDelegate(View view) {
        super(view);

        /* ... */

        UI.maybeChangeBackgroundColor(getContentView()); // TODO: __INJECT_CODE
    }

    /* ... */
}