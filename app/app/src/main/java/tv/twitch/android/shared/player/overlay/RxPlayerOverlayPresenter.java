package tv.twitch.android.shared.player.overlay;

import tv.purple.monolith.models.exception.VirtualImpl;
import tv.twitch.android.shared.theatre.data.pub.model.RxPlayerOverlayEvent;

public class RxPlayerOverlayPresenter {
    /* ... */

    public final void onBottomOverlayViewEventReceived(RxPlayerOverlayEvent rxPlayerOverlayEvent) {
        if (rxPlayerOverlayEvent instanceof RxPlayerOverlayEvent.RefreshClicked) {
            return;
        }

        /* ... */

        throw new VirtualImpl();
    }

    /* ... */
}
