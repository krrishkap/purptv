package tv.twitch.android.core.adapters;

import java.util.List;

import tv.purple.monolith.features.chat.bridge.ChatHook;
import tv.purple.monolith.models.exception.VirtualImpl;

public class TwitchAdapter {
    private List<RecyclerAdapterItem> items;

    /* ... */

    public void addItems(List<? extends RecyclerAdapterItem> items) {
        /* ... */

        this.items.addAll(items);
        ChatHook.maybeAbItems(this.items); // TODO: __INJECT_CODE
        // notifyItemRangeInserted(size, items.size());

        /* ... */

        throw new VirtualImpl();
    }

    /* ... */
}
