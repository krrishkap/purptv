package com.bumptech.glide;

import android.content.Context;

import tv.purple.monolith.core.bridge.GlideIntegration;
import tv.twitch.android.shared.ui.elements.image.TwitchGlideModule;

public class GeneratedAppGlideModuleImpl {
    private final TwitchGlideModule appGlideModule = null;

    /* ... */

    public void registerComponents(Context context, Glide glide, Registry registry) {
        /* ... */

        GlideIntegration.registerWebpGlideLibraryModule(appGlideModule, context, glide, registry); // TODO: __INJECT_CODE

        /* ... */
    }

    /* ... */
}
