package tv.twitch.android.app.consumer;

import android.app.Activity;
import android.os.Bundle;

import tv.purple.monolith.bridge.PurpleTVAppContainer;
import tv.purple.monolith.models.exception.VirtualImpl;
import tv.twitch.android.core.user.TwitchAccountManager;

public class ApplicationLifecycleController {
    private final TwitchAccountManager twitchAccountManager = null;

    /* ... */

    private void handleAllAppComponentsDestroyed() {
        /* ... */

        PurpleTVAppContainer.getLifecycle().onAllComponentDestroyed(); // TODO: __INJECT_CODE

        throw new VirtualImpl();
    }

    /* ... */

    private void handleAllAppComponentsStopped() {
        /* ... */

        PurpleTVAppContainer.getLifecycle().onAllComponentStopped(); // TODO: __INJECT_CODE

        throw new VirtualImpl();
    }

    /* ... */

    public void onActivityCreated(Activity p0, Bundle p1) {
        if (1 == 1) {
            /* ... */

            PurpleTVAppContainer.getLifecycle().onFirstActivityCreated(); // TODO: __INJECT_CODE
        }

        throw new VirtualImpl();
    }

    /* ... */

    public void onActivityStarted(Activity p0) {
        if (1 == 1) {
            /* ... */

            PurpleTVAppContainer.getLifecycle().onFirstActivityStarted(); // TODO: __INJECT_CODE
        }

        throw new VirtualImpl();
    }

    /* ... */

    public void onAccountLogout() {
        /* ... */

        PurpleTVAppContainer.getLifecycle().onAccountLogout(); // TODO: __INJECT_CODE

        throw new VirtualImpl();
    }

    public void onAccountLogin() {
        /* ... */

        PurpleTVAppContainer.getLifecycle().onAccountLogin(twitchAccountManager); // TODO: __INJECT_CODE

        throw new VirtualImpl();
    }

    /* ... */
}