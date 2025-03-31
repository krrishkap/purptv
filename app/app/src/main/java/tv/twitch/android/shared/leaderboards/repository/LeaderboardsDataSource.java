package tv.twitch.android.shared.leaderboards.repository;

import tv.purple.monolith.features.ui.UI;
import tv.purple.monolith.features.ui.bridge.UIHook;
import tv.purple.monolith.models.exception.VirtualImpl;
import tv.twitch.android.models.chat.ChatBroadcaster;
import tv.twitch.android.models.leaderboard.ChannelLeaderboards;

public class LeaderboardsDataSource {
    /* ... */

    public final boolean shouldFetchLeaderboards(ChatBroadcaster chatBroadcaster) {
        if (UIHook.getHideLeaderboards()) { // TODO: __INJECT_CODE
            return false;
        }

        /* ... */

        throw new VirtualImpl();
    }

    public final void observePubSubUpdates(ChannelLeaderboards channelLeaderboards) {
        if (UIHook.getHideLeaderboards()) { // TODO: __INJECT_CODE
            return;
        }

        /* ... */

        throw new VirtualImpl();
    }

    /* ... */
}
