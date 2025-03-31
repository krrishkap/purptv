package tv.twitch.android.shared.player.overlay.seekable;

import tv.purple.monolith.features.ui.bridge.UIHook;
import tv.purple.monolith.models.exception.VirtualImpl;

public class PlayPauseFastSeekViewDelegate {
    /* ... */

    private void setTouchListenersForFastSeeking() {
        /* ... */

        int fastSeekRewind = UIHook.getRewindSeek(); // TODO: __HOOK
        int fastSeekForward = UIHook.getForwardSeek(); // TODO: __HOOK

        /* ... */

        throw new VirtualImpl();
    }

    /* ... */
}
