package tv.twitch.android.shared.preferences;

import tv.purple.monolith.features.ui.bridge.UIHook;

public class VideoDebugConfig {
    /* ... */

    public final boolean shouldShowVideoDebugPanel() { // TODO: __REPLACE_METHOD
        return UIHook.getShouldShowVideoDebugPanel();
    }

    /* ... */
}
