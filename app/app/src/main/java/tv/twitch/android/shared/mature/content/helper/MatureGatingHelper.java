package tv.twitch.android.shared.mature.content.helper;

import java.util.List;

import tv.purple.monolith.core.models.flag.Flag;
import tv.purple.monolith.features.ui.bridge.UIHook;
import tv.purple.monolith.models.exception.VirtualImpl;
import tv.twitch.android.models.ads.MatureGatingModel;
import tv.twitch.android.models.contentclassification.ContentClassification;

public class MatureGatingHelper {
    /* ... */

    public final boolean willShowMatureWarning(boolean z, List<String> contentLabels) {
        if (UIHook.getDisableMatureContent()) {
            return false;
        }

        throw new VirtualImpl();
    }

    public final boolean willShowMatureWarningForStream(ContentClassification contentClassification) {
        if (UIHook.getDisableMatureContent()) {
            return false;
        }

        throw new VirtualImpl();
    }

    public final MatureGatingModel createMatureGatingModel(ContentClassification contentClassification) {
        if (UIHook.getDisableMatureContent()) {
            return new MatureGatingModel.NotGated(contentClassification);
        }

        throw new VirtualImpl();
    }

    /* ... */
}

