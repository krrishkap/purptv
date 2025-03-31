package tv.twitch.android.shared.emotes.emotepicker.adapter;

import tv.purple.monolith.features.chat.bridge.PurpleTVEmoteUiModelWithStaticUrl;
import tv.purple.monolith.models.exception.VirtualImpl;
import tv.twitch.android.shared.emotes.emotepicker.models.EmoteUiModel;

public class EmoteAdapterItem {
    /* ... */

    public void bindToViewHolder(Object viewHolder) {
        /* ... */

        // TODO: __HOOK_URL
        EmoteUiModel model = null;
        if (model instanceof PurpleTVEmoteUiModelWithStaticUrl) {
            String url = ((PurpleTVEmoteUiModelWithStaticUrl) model).getUrl(); // TODO: __INJECT_CODE
        }

        /* ... */

        throw new VirtualImpl();
    }

    /* ... */
}