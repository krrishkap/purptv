package tv.twitch.android.shared.player.overlay;

import android.widget.ImageView;

import androidx.annotation.NonNull;

import kotlin.jvm.internal.DefaultConstructorMarker;
import tv.purple.monolith.bridge.ex.IRxBottomPlayerControlOverlayViewDelegate;
import tv.purple.monolith.core.LoggerImpl;
import tv.purple.monolith.features.refreshstream.bridge.RefreshStreamHook;
import tv.purple.monolith.models.exception.VirtualImpl;
import tv.twitch.android.core.mvp.viewdelegate.RxViewDelegate;
import tv.twitch.android.core.mvp.viewdelegate.ViewDelegateEvent;
import tv.twitch.android.core.mvp.viewdelegate.ViewDelegateState;
import tv.twitch.android.shared.player.overlay.databinding.BottomPlayerOverlayControlsBinding;

public class RxBottomPlayerControlOverlayViewDelegate extends RxViewDelegate<ViewDelegateState, RxBottomPlayerControlOverlayViewDelegate.Event> implements IRxBottomPlayerControlOverlayViewDelegate { //TODO: __IMPL
    private final BottomPlayerOverlayControlsBinding viewBinding = null;

    public RxBottomPlayerControlOverlayViewDelegate(BottomPlayerOverlayControlsBinding binding) {
        super(null);

        /* ... */

        RefreshStreamHook.bind(this, viewBinding.refreshStreamButton); // TODO: __INJECT_CODE
    }

    @Override
    public void pushRefreshEvent() { // TODO: __INJECT_METHOD
        LoggerImpl.INSTANCE.debug("pushRefreshEvent");
        pushEvent(Event.RefreshClicked.INSTANCE);
    }

    @NonNull
    @Override
    public ImageView getRefreshButton() { // TODO: __INJECT_METHOD
        return viewBinding.refreshStreamButton;
    }

    public static abstract class Event implements ViewDelegateEvent {
        public Event(DefaultConstructorMarker var1) {
            /* ... */

            throw new VirtualImpl();
        }

        /* ... */

        public static final class RefreshClicked extends Event {
            public static final RefreshClicked INSTANCE = new RefreshClicked();

            private RefreshClicked() {
                super(null);
            }
        }
    }
}