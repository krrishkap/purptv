package tv.twitch.android.shared.subscriptions.button;

import tv.purple.monolith.core.models.flag.Flag;
import tv.purple.monolith.models.exception.VirtualImpl;
import tv.twitch.android.core.mvp.presenter.RxPresenter;

public class TheatreFollowSubscribeButtonPresenter extends RxPresenter {
    /* ... */

    public final void show() {
        if (Flag.HIDE_FSB.asBoolean()) { // TODO: __INJECT_CODE
            return;
        }

        /* ... */

        throw new VirtualImpl();
    }

    /* ... */
}
