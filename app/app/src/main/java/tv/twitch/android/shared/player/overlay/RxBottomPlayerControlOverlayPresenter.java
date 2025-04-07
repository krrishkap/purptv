package tv.twitch.android.shared.player.overlay;

import dagger.internal.Provider;
import tv.purple.monolith.bridge.ex.IRxBottomPlayerControlOverlayPresenter;
import tv.purple.monolith.features.refreshstream.bridge.RefreshStreamHook;
import tv.twitch.android.core.data.source.DataUpdater;
import tv.twitch.android.core.mvp.presenter.RxPresenter;
import tv.twitch.android.shared.theatre.data.pub.model.TheatreCoordinatorRequest;

public class RxBottomPlayerControlOverlayPresenter extends RxPresenter implements IRxBottomPlayerControlOverlayPresenter {
    private Provider<DataUpdater<TheatreCoordinatorRequest>> theatreRequestUpdater = null; // TODO: __INJECT_FIELD

    private final void onViewEventReceived(RxBottomPlayerControlOverlayViewDelegate.Event event) {
        if (event instanceof RxBottomPlayerControlOverlayViewDelegate.Event.RefreshClicked) { // TODO: __INJECT_CODE
            RefreshStreamHook.tryPushRefreshRequest(theatreRequestUpdater);
            return;
        }

        /* ... */
    }

    @Override
    public void inject(Provider<DataUpdater<TheatreCoordinatorRequest>> provider) { // TODO: __INJECT_METHOD
        theatreRequestUpdater = provider;
    }
}
