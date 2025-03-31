package tv.twitch.android.shared.leaderboards.repository.pubsub;

import io.reactivex.Flowable;
import tv.purple.monolith.core.bridge.CoreHook;
import tv.purple.monolith.models.exception.VirtualImpl;
import tv.twitch.android.shared.leaderboards.model.LeaderboardPubSubRankingUpdate;
import tv.twitch.android.shared.leaderboards.repository.LeaderboardsDataState;

public class LeaderboardPubSubUpdater {
    /* ... */

    public final Flowable<LeaderboardPubSubRankingUpdate> observeEventsForLeaderboard(final String leaderboardId, Flowable<LeaderboardsDataState> dataStateObserver) {
        if (CoreHook.isBadLeaderboardId(leaderboardId)) { // TODO: __INJECT_CODE
            return Flowable.empty();
        }

        /* ... */

        throw new VirtualImpl();
    }

    /* ... */
}