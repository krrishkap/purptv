package tv.twitch.android.shared.analytics;

import java.util.Map;

import tv.purple.monolith.core.LoggerImpl;
import tv.twitch.android.network.retrofit.ApiCallback;

public class AnalyticsTracker {
    /* ... */

    private final void trackEvent(String str, Map<String, Object> map, ApiCallback<Void> apiCallback, boolean z) {
        /* ... */

        LoggerImpl.debugTrackEvent(str, map); // TODO: __INJECT_CODE
    }

    /* ... */
}
