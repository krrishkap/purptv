package tv.twitch.android.shared.mature.content.stopsign;

import io.reactivex.Flowable;
import tv.purple.monolith.core.models.flag.Flag;
import tv.purple.monolith.features.ui.bridge.UIHook;
import tv.purple.monolith.models.exception.VirtualImpl;
import tv.twitch.android.models.contentclassification.DisclosureModel;
import tv.twitch.android.models.contentclassification.StopSignModel;

public class VodStopSignDataSource {
    public Flowable<DisclosureModel> observeDisclosureUpdates() {
        if (UIHook.getDisableMatureContent()) {
            return Flowable.just(new DisclosureModel.NotShowing(false));
        }

        /* ... */

        throw new VirtualImpl();
    }

    public Flowable<StopSignModel> observeStopSignUpdates() {
        if (UIHook.getDisableMatureContent()) {
            return Flowable.just(new StopSignModel.None(false));
        }

        /* ... */

        throw new VirtualImpl();
    }
}
