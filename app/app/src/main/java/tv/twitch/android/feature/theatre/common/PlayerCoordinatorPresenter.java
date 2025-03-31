package tv.twitch.android.feature.theatre.common;

import androidx.fragment.app.FragmentActivity;

import tv.purple.monolith.models.exception.VirtualImpl;
import tv.twitch.android.core.mvp.presenter.BasePresenter;
import tv.twitch.android.shared.player.presenters.PlayerPresenter;

public class PlayerCoordinatorPresenter extends BasePresenter {
    private PlayerPresenter playerPresenter;

    /* ... */

    private void playWithCurrentModeAndQuality() {
        throw new VirtualImpl();
    }

    public final void refreshStream() { // TODO: __INJECT_METHOD
        playerPresenter.stop();
        playWithCurrentModeAndQuality();
    }

    public final FragmentActivity getActivity$feature_theatre_release() {
        /* ... */

        throw new VirtualImpl();
    }

    /* ... */
}
