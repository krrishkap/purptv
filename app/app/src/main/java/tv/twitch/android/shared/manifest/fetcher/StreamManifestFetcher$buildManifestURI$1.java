package tv.twitch.android.shared.manifest.fetcher;

import android.net.Uri;

import tv.purple.monolith.core.bridge.CoreHook;
import tv.purple.monolith.models.exception.VirtualImpl;

public class StreamManifestFetcher$buildManifestURI$1 {
    /* ... */

    public final Uri invoke(String token, String sig) {
        /* ... */

        String fbValue = "true";
        fbValue = CoreHook.hookFastBreadArg(fbValue); // TODO: __INJECT_CODE

        throw new VirtualImpl();
    }

    /* ... */
}