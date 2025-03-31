package tv.twitch.android.shared.leaderboards.webview;

import tv.purple.monolith.features.ui.bridge.UIHook;
import tv.purple.monolith.models.exception.VirtualImpl;
import tv.twitch.android.models.chat.ChatBroadcaster;

public class LeaderboardsWebViewPresenter {
    /* ... */

    public final boolean shouldFetchLeaderboards(ChatBroadcaster chatBroadcaster) {
        if (UIHook.getHideLeaderboards()) { // TODO: __INJECT_CODE
            return false;
        }

        /* ... */

        throw new VirtualImpl();
    }

    /* ... */
}
