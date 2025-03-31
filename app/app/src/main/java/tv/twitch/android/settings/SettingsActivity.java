package tv.twitch.android.settings;

import android.os.Bundle;

import tv.purple.monolith.features.ui.bridge.UIHook;
import tv.twitch.android.core.activities.BaseRxMVPActivity;

public class SettingsActivity extends BaseRxMVPActivity {
    public void onCreate(Bundle var1) {
        super.onCreate(var1);

        /* ... */

        UIHook.maybeShowAlphaBuildDialog(this); // TODO: __INJECT_CODE
    }
}
