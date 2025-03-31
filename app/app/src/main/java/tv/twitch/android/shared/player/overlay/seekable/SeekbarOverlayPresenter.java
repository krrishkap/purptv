package tv.twitch.android.shared.player.overlay.seekable;

import tv.purple.monolith.bridge.ex.ISeekbarOverlayPresenter;

public class SeekbarOverlayPresenter implements ISeekbarOverlayPresenter { // TODO: __IMPLEMENT
    private SeekbarOverlayViewDelegate seekbarOverlayViewDelegate;

    /* ... */

    @Override
    public void xSeekTo(int pos) { // TODO: __INJECT_METHOD
        seekbarOverlayViewDelegate.xSeekTo(pos);
    }

    /* ... */
}
