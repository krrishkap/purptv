package tv.twitch.android.shared.player.presenters;

import android.content.Context;

import tv.purple.monolith.models.exception.VirtualImpl;
import tv.twitch.android.models.player.VideoRequestPlayerType;
import tv.twitch.android.provider.experiments.ExperimentHelper;
import tv.twitch.android.shared.player.audiofocus.AudioFocusRepository;
import tv.twitch.android.shared.player.factory.PlayerFactory;
import tv.twitch.android.shared.player.models.PlayerConfiguration;
import tv.twitch.android.shared.player.trackers.PlayerTracker;

public abstract class SeekablePlayerPresenter extends BasePlayerPresenter {
    public SeekablePlayerPresenter(VideoRequestPlayerType var1, String var2, Context var3, PlayerTracker.Factory var4, PlayerFactory var5, ExperimentHelper var6, AudioFocusRepository var7, PlayerConfiguration var8, DefaultPlayerPresenterTrackingProperties var9) {
        super(null, null, null, null, null, null, null, null, null);
        throw new VirtualImpl();
    }
    /* ... */
}
