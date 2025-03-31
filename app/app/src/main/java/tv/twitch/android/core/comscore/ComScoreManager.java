package tv.twitch.android.core.comscore;

import tv.purple.monolith.core.models.flag.Flag;

public class ComScoreManager {
    /* ... */

    private final void enableComScore() {
        if (Flag.DISABLE_COMSCORE.asBoolean()) { // TODO: __INJECT_CODE
            disableComScore();
            return;
        }

        /* ... */
    }

    private final void disableComScore() {
        /* ... */
    }

    /* ... */
}
