package tv.twitch.android.shared.ui.cards.vod;

import tv.purple.monolith.features.vodhunter.bridge.VodHunterHook;
import tv.twitch.android.models.videos.VodModel;

public class VodRecyclerItem {
    public static final class VodVideoCardViewHolder {
        public void onBindVideoDataItem(VodRecyclerItem item) {
            /* ... */

            VodModel model = null;

            if (model.isRestricted() && !VodHunterHook.isEnabled()) { // TODO: __INJECT_CODE
                /* ... */
            }

            /* ... */
        }
    }
}
