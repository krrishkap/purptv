package tv.twitch.android.app.core;

import tv.purple.monolith.core.models.flag.Flag;
import tv.purple.monolith.models.exception.VirtualImpl;

public class GooglePlayServicesHelper {
    /* ... */

    public final boolean arePlayServicesAvailable() {
        if (Flag.DISABLE_GOOGLE_PLAY_SERVICES.asBoolean()) { // TODO: __INJECT_CODE
            return false;
        }

        /* ... */

        throw new VirtualImpl();
    }

    /* ... */
}
