package tv.twitch.android.shared.ui.elements.image;

import android.content.Context;

import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;

import tv.purple.monolith.core.bridge.CoreHook;

public class TwitchGlideModule {
    /* ... */

    public void applyOptions(Context context, GlideBuilder builder) {
        /* ... */

        builder.setDiskCache(new InternalCacheDiskCacheFactory(context, CoreHook.getGlideCacheSize())); // TODO: __REPLACE_CODE
    }

    /* ... */
}
