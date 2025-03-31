package tv.twitch.android.feature.theatre.refactor;

import tv.purple.monolith.core.LoggerImpl;
import tv.purple.monolith.features.refreshstream.bridge.RefreshStreamHook;
import tv.purple.monolith.models.exception.VirtualImpl;
import tv.twitch.android.shared.theatre.data.pub.model.RxPlayerOverlayEvent;

public class TheatreViewCoordinatorPresenter$observePlayerOverlayEvents$1 {
    final TheatreViewCoordinatorPresenter this$0 = null;

    public final void invoke(RxPlayerOverlayEvent event) {
        if (event instanceof RxPlayerOverlayEvent.RefreshClicked) { // TODO: __INJECT_CODE
            LoggerImpl.INSTANCE.debug("test1");
            RefreshStreamHook.tryPushRefreshRequest(this$0);
            return;
        }

        throw new VirtualImpl();
    }
}
