package tv.twitch.android.shared.ads.debug;

import tv.purple.monolith.core.models.flag.Flag;
import tv.purple.monolith.features.ui.bridge.UIHook;
import tv.purple.monolith.models.exception.VirtualImpl;

public class ContentClassificationWarningProvider {
    public final boolean shouldForceShowWarning() {
        if (UIHook.getDisableMatureContent()) { // TODO: __INJECT_CODE
            return false;
        }

        throw new VirtualImpl();
    }
}
