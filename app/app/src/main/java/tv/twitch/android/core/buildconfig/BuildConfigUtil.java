package tv.twitch.android.core.buildconfig;

import tv.purple.monolith.core.bridge.CoreHook;
import tv.purple.monolith.models.exception.VirtualImpl;

public final class BuildConfigUtil {
    public static final BuildConfigUtil INSTANCE = new BuildConfigUtil();

    public final boolean isAlpha() { // TODO: __REPLACE_METHOD
        return CoreHook.getDevMode();
    }

    public final boolean isBeta() { // TODO: __REPLACE_METHOD
        return CoreHook.getDevMode();
    }

    public final boolean isDebugConfigEnabled() { // TODO: __REPLACE_METHOD
        return CoreHook.getDevMode();
    }

    private BuildConfigUtil() {
        throw new VirtualImpl();
    }

    public final boolean shouldShowDebugOptions(boolean z) { // TODO: __REPLACE_METHOD
        return CoreHook.getDevMode();
    }

    public final String getVersionNumber() {
        throw new VirtualImpl();
    }

    public final boolean bugReportingEnabled() { // TODO: __REPLACE_METHOD
        return CoreHook.getDevMode();
    }

    /* ... */
}
