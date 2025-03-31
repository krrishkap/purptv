package tv.twitch.android.feature.discovery.feed.following;

import java.util.List;

import tv.purple.monolith.features.ui.bridge.UIHook;
import tv.purple.monolith.models.exception.VirtualImpl;
import tv.twitch.android.feature.followed.model.FollowingContentItemCollection;

public class DiscoveryFeedFollowingAdapterBinder {
    /* ... */

    public final void bindCollections(List<? extends FollowingContentItemCollection> itemCollections, boolean z10, boolean z11) {
        itemCollections = UIHook.filterFollowingContentItemCollection(itemCollections); // TODO: __INJECT_CODE

        /* ... */

        throw new VirtualImpl();
    }

    /* ... */
}