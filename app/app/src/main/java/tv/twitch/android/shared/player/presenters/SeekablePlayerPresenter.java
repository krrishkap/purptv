package tv.twitch.android.shared.player.presenters;

import android.content.Context;

import tv.twitch.android.provider.experiments.ExperimentHelper;
import tv.twitch.android.shared.player.audiofocus.AudioFocusRepository;
import tv.twitch.android.shared.player.availability.PlayerAvailabilityTracker;
import tv.twitch.android.shared.player.factory.PlayerFactory;
import tv.twitch.android.shared.player.models.PlayerConfiguration;
import tv.twitch.android.shared.player.trackers.IPlayerPresenterTracker;

public abstract class SeekablePlayerPresenter extends BasePlayerPresenter {
    public SeekablePlayerPresenter(Context context, IPlayerPresenterTracker iPlayerPresenterTracker, PlayerFactory playerFactory, ExperimentHelper experimentHelper, PlayerAvailabilityTracker playerAvailabilityTracker, AudioFocusRepository audioFocusRepository, PlayerConfiguration playerConfiguration) {
        super(context, iPlayerPresenterTracker, playerFactory, experimentHelper, playerAvailabilityTracker, audioFocusRepository, playerConfiguration);
    }
    /* ... */
}
