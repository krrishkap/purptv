package tv.twitch.android.shared.player.overlay;

import tv.purple.monolith.core.LoggerImpl;
import tv.twitch.android.core.data.source.DataUpdater;
import tv.twitch.android.core.mvp.presenter.RxPresenter;
import tv.twitch.android.core.mvp.viewdelegate.EventDispatcher;
import tv.twitch.android.shared.theatre.data.pub.model.RxPlayerOverlayEvent;

public class RxBottomPlayerControlOverlayPresenter extends RxPresenter {
    private final EventDispatcher<RxPlayerOverlayEvent> viewEventDispatcher = null;
    private final DataUpdater<RxPlayerOverlayEvent> playerOverlayEventUpdater = null;

    private final void onViewEventReceived(RxBottomPlayerControlOverlayViewDelegate.Event event) {
        LoggerImpl.INSTANCE.debug(event.toString());
        if (event instanceof RxBottomPlayerControlOverlayViewDelegate.Event.RefreshClicked) { // TODO: __INJECT_CODE
            this.viewEventDispatcher.pushEvent(RxPlayerOverlayEvent.RefreshClicked.INSTANCE);
            return;
        }

        /* ... */
    }

}
