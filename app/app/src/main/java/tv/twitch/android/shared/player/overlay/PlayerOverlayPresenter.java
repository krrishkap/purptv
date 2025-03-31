package tv.twitch.android.shared.player.overlay;

import tv.purple.monolith.features.ui.bridge.UIHook;
import tv.purple.monolith.models.exception.VirtualImpl;
import tv.twitch.android.models.videos.VodModel;
import tv.twitch.android.shared.player.overlay.seekable.SeekbarOverlayPresenter;
import tv.twitch.android.shared.player.overlay.stream.StreamOverlayConfiguration;

public class PlayerOverlayPresenter {
    private PlayerOverlayViewDelegate viewDelegate;

    /* .. */

    public void onBindVodModel(VodModel model, SeekbarOverlayPresenter presenter) { // TODO: __INJECT_METHOD
        viewDelegate.onBindVodModel(model, presenter);
    }

    public final void layoutForOverlayConfiguration() {
        StreamOverlayConfiguration streamOverlayConfiguration = null;

        /* ... */

        UIHook.maybeHideLiveShareButton(streamOverlayConfiguration, viewDelegate); // TODO: __INJECT_METHOD

        /* ... */

        throw new VirtualImpl();
    }

    public void onBindClip() {
        viewDelegate.onBindClip();
    }

    public void onInflateViewDelegate() {
        viewDelegate.onInflateViewDelegate();
    }
}