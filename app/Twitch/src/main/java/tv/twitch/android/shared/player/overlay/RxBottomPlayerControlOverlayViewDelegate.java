package tv.twitch.android.shared.player.overlay;

import android.view.View;

import kotlin.NotImplementedError;
import kotlin.jvm.internal.DefaultConstructorMarker;
import tv.twitch.android.core.mvp.viewdelegate.RxViewDelegate;
import tv.twitch.android.core.mvp.viewdelegate.ViewDelegateEvent;

public class RxBottomPlayerControlOverlayViewDelegate extends RxViewDelegate<RxBottomPlayerControlOverlayPresenter.State, RxBottomPlayerControlOverlayViewDelegate.Event> {
    public RxBottomPlayerControlOverlayViewDelegate(View view) {
        super(view);
    }

    /* ... */

    @Override
    public void render(RxBottomPlayerControlOverlayPresenter.State state) {
        /* ... */

        throw new NotImplementedError();
    }

    /* ... */

    public static abstract class Event implements ViewDelegateEvent {
        public Event(DefaultConstructorMarker var1) {
            /* ... */

            throw new NotImplementedError();
        }

        public static final class RotateClicked extends Event {
            public static final RotateClicked INSTANCE = new RotateClicked();

            private RotateClicked() {
                super(null);
            }
        }

        public static final class RefreshClicked extends Event {
            public static final RefreshClicked INSTANCE = new RefreshClicked();

            private RefreshClicked() {
                super(null);
            }
        }


        /* ... */
    }

    /* ... */
}
