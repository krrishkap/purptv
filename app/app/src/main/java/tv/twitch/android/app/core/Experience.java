package tv.twitch.android.app.core;

import android.content.Context;

import tv.purple.monolith.features.ui.UI;

public class Experience {
    /* ... */

    public final boolean isTablet() {
        boolean isTablet = false;
        return UI.hookIsTablet(isTablet); // TODO: __INJECT_CODE
    }

    public final boolean shouldShowTabletUI(Context context) {
        boolean isTablet = false;
        return UI.hookIsTablet(isTablet); // TODO: __INJECT_CODE
    }
    /* ... */
}
