package tv.twitch.android.feature.theatre.common;

import android.view.ViewGroup;

import tv.purple.monolith.features.ui.UI;
import tv.purple.monolith.models.exception.VirtualImpl;

public class PlayerCoordinatorViewDelegate {
    private ViewGroup landscapeChatContainer;

    /* ... */

    public PlayerCoordinatorViewDelegate() {
        /* ... */

        UI.changeLandscapeChatContainerOpacity(landscapeChatContainer); // TODO: __INJECT_CODE

        throw new VirtualImpl();
    }

    /* ... */
}
