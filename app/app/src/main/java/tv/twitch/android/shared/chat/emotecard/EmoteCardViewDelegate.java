package tv.twitch.android.shared.chat.emotecard;

import android.view.View;
import android.widget.LinearLayout;

import tv.purple.monolith.features.ui.bridge.UIHook;
import tv.twitch.android.core.mvp.viewdelegate.BaseViewDelegate;

public class EmoteCardViewDelegate extends BaseViewDelegate {
    private LinearLayout followButton;

    public EmoteCardViewDelegate(View view) {
        super(view);
    }

    /* ... */

    private final void renderEmoteCard(EmoteCardState.Loaded loaded) {
        EmoteCardUiModel emoteCardUiModel = loaded.getEmoteCardUiModel();
        FollowButtonUiModel followButtonUiModel = emoteCardUiModel.getFollowButton();
        /* ... */

        UIHook.maybeHideFollowButton(followButton, followButtonUiModel); // TODO: __INJECT_CODE
    }

    /* ... */
}
