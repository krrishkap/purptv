package tv.twitch.android.feature.sponsored.streams;

import io.reactivex.Flowable;
import tv.twitch.android.models.ads.SponsoredStream;

public class SponsoredStreamsLiveDataSource {
    /* ... */

    public Flowable<SponsoredStream> observeModelUpdates() { // TODO: __REPLACE_METHOD
        return Flowable.just(SponsoredStream.NotSponsored.INSTANCE);
    }

    /* ... */
}
