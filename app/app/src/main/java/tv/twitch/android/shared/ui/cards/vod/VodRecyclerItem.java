package tv.twitch.android.shared.ui.cards.vod;

import tv.purple.monolith.features.vodhunter.VodHunter;
import tv.twitch.android.models.videos.VodModel;

public class VodRecyclerItem {
    public static final class VodVideoCardViewHolder {
        public void onBindVideoDataItem(VodRecyclerItem item) {
            /* ... */

            VodModel model = null;

            if (model.isRestricted() && !VodHunter.isEnabled()) { // TODO: __INJECT_CODE
                /* ... */
            }

            /* ... */
        }
    }
}
