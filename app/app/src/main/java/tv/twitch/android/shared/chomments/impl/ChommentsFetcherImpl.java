package tv.twitch.android.shared.chomments.impl;

import java.util.List;

import io.reactivex.Maybe;
import tv.purple.monolith.bridge.PurpleTVAppContainer;
import tv.purple.monolith.features.vodsync.bridge.VodSyncHook;
import tv.purple.monolith.models.exception.VirtualImpl;
import tv.twitch.android.models.channel.ChannelModel;
import tv.twitch.android.models.chomments.ChommentModel;
import tv.twitch.android.models.videos.VodModel;

public class ChommentsFetcherImpl {
    private VodModel vodModel;

    /* ... */

    public void prepareForVod(VodModel vod, ChannelModel channelModel) {
        /* ... */

        int channelId = channelModel.getId(); // TODO: __INJECT_CODE
        PurpleTVAppContainer.getLifecycle().onConnectingToChannel(channelId); // TODO: __INJECT_CODE
        PurpleTVAppContainer.getLifecycle().onConnectedToChannel(channelId); // TODO: __INJECT_CODE

        /* ... */

        throw new VirtualImpl();
    }

    public Maybe<List<ChommentModel>> fetchChommentsForTimestamp(String playableId, int timestamp) {
        timestamp = VodSyncHook.hookChommentTimestamp(vodModel, timestamp); // TODO: __INJECT_CODE
        if (timestamp < 0) {
            return Maybe.empty();
        }

        Object o = null;
        o.toString();
        o = new String("");

        throw new VirtualImpl();
    }

    /* ... */
}