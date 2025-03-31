package tv.twitch.android.shared.player.overlay.databinding;

import android.view.View;
import android.widget.ImageView;

import androidx.constraintlayout.widget.ConstraintLayout;

import tv.purple.monolith.features.refreshstream.bridge.RefreshStreamHook;
import tv.purple.monolith.models.exception.VirtualImpl;

public class BottomPlayerOverlayControlsBinding {
    public ImageView refreshStreamButton = null; // TODO: __INJECT_FIELD

    /* ... */

    public ConstraintLayout getRoot() {
        throw new VirtualImpl();
    }

    public static BottomPlayerOverlayControlsBinding bind(View view) {
        /* ... */

        BottomPlayerOverlayControlsBinding instance = new BottomPlayerOverlayControlsBinding();
        instance.bindPurpleTVStuff(); // TODO: __INJECT_CODE

        /* ... */

        throw new VirtualImpl();
    }

    private void bindPurpleTVStuff() { // TODO: __INJECT_METHOD
        refreshStreamButton = RefreshStreamHook.getRefreshStreamButton(getRoot());
    }

    /* ... */
}
