package tv.twitch.android.shared.autoplay;

import tv.purple.monolith.core.models.flag.Flag;
import tv.purple.monolith.models.exception.VirtualImpl;
import tv.twitch.android.models.Playable;

public class RecommendationAutoPlayPresenter<T extends Playable> {
    /* ... */

    public final void prepareRecommendationForCurrentModel(T var1) {
        /* ... */

        if (!Flag.DISABLE_THEATRE_AUTOPLAY.asBoolean()) {
            // ISubscriptionHelper.DefaultImpls.asyncSubscribe$default(this, this.recommendationFetcher.fetchRecommendation(var1), (DisposeOn)null, new NamelessClass_5(this), 1, (Object)null);
        }

        /* ... */

        throw new VirtualImpl();
    }

    /* ... */
}
