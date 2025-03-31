package tv.twitch.android.settings.main;

import androidx.fragment.app.FragmentActivity;

import tv.purple.monolith.features.settings.bridge.PurpleTVSettingsHook;
import tv.twitch.android.shared.settings.BaseSettingsPresenter;
import tv.twitch.android.shared.settings.SettingsNavigationController;
import tv.twitch.android.shared.settings.SettingsTracker;
import tv.twitch.android.shared.ui.menus.SettingsPreferencesController;
import tv.twitch.android.shared.ui.menus.core.MenuAdapterBinder;

public class MainSettingsPresenter extends BaseSettingsPresenter {
    public MainSettingsPresenter(FragmentActivity fragmentActivity, MenuAdapterBinder menuAdapterBinder, SettingsTracker settingsTracker) {
        super(fragmentActivity, menuAdapterBinder, settingsTracker);
    }

    @Override
    protected SettingsNavigationController getNavController() {
        return null;
    }

    @Override
    protected SettingsPreferencesController getPrefController() {
        return null;
    }

    @Override
    public String getToolbarTitle() {
        return null;
    }

    public void updateSettingModels() {
        /* ... */

        PurpleTVSettingsHook.injectOrangeSettingsMenuModel(getSettingModels()); // TODO: __INJECT_CODE
    }
}
