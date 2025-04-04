package tv.purple.monolith.features.settings.bridge.settings

import androidx.fragment.app.FragmentActivity
import tv.purple.monolith.bridge.RES_STRINGS
import tv.purple.monolith.core.ResManager.fromResToString
import tv.purple.monolith.features.settings.bridge.factory.TwitchItemsFactory
import tv.purple.monolith.features.settings.component.PurpleTVSettingsController
import tv.twitch.android.shared.settings.BaseSettingsPresenter
import tv.twitch.android.shared.settings.SettingsNavigationController
import tv.twitch.android.shared.settings.SettingsTracker
import tv.twitch.android.shared.ui.menus.SettingsPreferencesController
import tv.twitch.android.shared.ui.menus.core.MenuAdapterBinder

class PurpleTVSettingsPresenter(
    activity: FragmentActivity,
    adapterBinder: MenuAdapterBinder,
    settingsTracker: SettingsTracker,
    private val controller: PurpleTVSettingsController,
    factory: TwitchItemsFactory
) : BaseSettingsPresenter(activity, adapterBinder, settingsTracker) {
    override fun getNavController(): SettingsNavigationController {
        return controller
    }

    override fun getPrefController(): SettingsPreferencesController {
        return controller
    }

    override fun getToolbarTitle(): String {
        return RES_STRINGS.purpletv_settings_menu_main.fromResToString()
    }

    override fun updateSettingModels() {
        settingModels.clear()
        settingModels.addAll(controller.getMainSettingModels())
    }
}