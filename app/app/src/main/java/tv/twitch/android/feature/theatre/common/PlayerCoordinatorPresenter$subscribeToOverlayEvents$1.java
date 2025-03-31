package tv.twitch.android.feature.theatre.common;

import tv.purple.monolith.models.exception.VirtualImpl;
import tv.twitch.android.shared.player.overlay.PlayerOverlayEvents;

public class PlayerCoordinatorPresenter$subscribeToOverlayEvents$1 {
    PlayerCoordinatorPresenter this$0;

    /* ... */

    public final void invoke(PlayerOverlayEvents event) {
        if (event instanceof PlayerOverlayEvents.Refresh) { // TODO: __INJECT_CODE
            this$0.refreshStream();
            return;
        }
        if (event instanceof PlayerOverlayEvents.ChangePlaybackSpeed) { // TODO: __INJECT_CODE
            return;
        }

        /* ... */

        throw new VirtualImpl();
    }

    /* ... */
}