package tv.twitch.android.core.adapters;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import tv.purple.monolith.features.chat.bridge.ChatHook;
import tv.purple.monolith.models.exception.VirtualImpl;

public abstract class AbstractTwitchRecyclerViewHolder extends RecyclerView.ViewHolder {
    /* ... */

    public AbstractTwitchRecyclerViewHolder(@NonNull View itemView) {
        super(itemView);

        throw new VirtualImpl();
    }

    /* ... */

    public final void onBindData(RecyclerAdapterItem item) {
        /* ... */

        ChatHook.onBindToViewHolder(this, item); // TODO: __INJECT_CODE // TODO: __INJECT_CODE

        throw new VirtualImpl();
    }

    public void onBindDataItem(RecyclerAdapterItem item) {
        /* ... */

        ChatHook.onBindToViewHolder(this, item); // TODO: __INJECT_CODE

        throw new VirtualImpl();
    }

    public void onRecycled() {
        throw new VirtualImpl();
    }

    /* ... */
}
