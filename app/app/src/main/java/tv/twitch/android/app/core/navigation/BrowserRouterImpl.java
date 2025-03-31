package tv.twitch.android.app.core.navigation;

import android.net.Uri;

import androidx.fragment.app.FragmentActivity;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import tv.purple.monolith.core.models.flag.Flag;
import tv.purple.monolith.features.chat.bridge.ChatHook;
import tv.purple.monolith.features.ui.bridge.UIHook;
import tv.purple.monolith.models.exception.VirtualImpl;

public class BrowserRouterImpl {
    /* ... */

    private final void showTwitchDialogForBrowserWithUri(FragmentActivity fragmentActivity, Uri uri, Function0<Unit> function0, boolean z) {
        if (UIHook.getDisableLinkDisclaimer()) { // TODO: __INJECT_CODE
            queryPackageManagerForBrowserIntent(fragmentActivity, uri, function0, true);
            return;
        }

        throw new VirtualImpl();
    }

    /* ... */

    private final void queryPackageManagerForBrowserIntent(FragmentActivity fragmentActivity, Uri uri, Function0<Unit> function0, boolean z) {
        throw new VirtualImpl();
    }

    /* ... */
}
