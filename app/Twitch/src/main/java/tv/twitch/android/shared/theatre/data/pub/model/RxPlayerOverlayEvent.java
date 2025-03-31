package tv.twitch.android.shared.theatre.data.pub.model;

import kotlin.NotImplementedError;
import kotlin.jvm.internal.DefaultConstructorMarker;

public class RxPlayerOverlayEvent {
    public RxPlayerOverlayEvent(DefaultConstructorMarker var1) {
        throw new NotImplementedError();
    }

    /* ... */

    public static final class RefreshClicked extends RxPlayerOverlayEvent { // TODO: __INJECT_CLASS
        public static final RefreshClicked INSTANCE = new RefreshClicked();

        private RefreshClicked() {
            super((DefaultConstructorMarker) null);
        }
    }

    /* ... */
}

