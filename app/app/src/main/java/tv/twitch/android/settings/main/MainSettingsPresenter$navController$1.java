package tv.twitch.android.settings.main;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import tv.purple.monolith.features.settings.bridge.PurpleTVSettingsHook;
import tv.purple.monolith.models.exception.VirtualImpl;
import tv.twitch.android.models.settings.SettingsDestination;

public class MainSettingsPresenter$navController$1 {
    /* ... */

    public void navigateToSettingFragment(SettingsDestination settingsDestination, Bundle bundle) {
        Fragment accountSettingsFragment = null;

        /* ... */


        if (settingsDestination == SettingsDestination.PurpleSettings) { // TODO: __INJECT_CODE
            accountSettingsFragment = PurpleTVSettingsHook.createSettingsFragment();
        }

        /* ... */

        accountSettingsFragment.getActivity();

        /* ... */

        throw new VirtualImpl();
    }

    public void updateSettingModels() {
        throw new VirtualImpl();
    }
    /* ... */
}