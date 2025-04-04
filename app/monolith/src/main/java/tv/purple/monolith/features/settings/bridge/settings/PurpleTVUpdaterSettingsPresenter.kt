package tv.purple.monolith.features.settings.bridge.settings

import androidx.fragment.app.FragmentActivity
import tv.purple.monolith.bridge.RES_STRINGS
import tv.purple.monolith.core.ResManager.fromResToString
import tv.purple.monolith.features.settings.bridge.factory.TwitchItemsFactory
import tv.purple.monolith.features.settings.bridge.model.PurpleTVSubMenu
import tv.purple.monolith.features.settings.component.PurpleTVSettingsController
import tv.purple.monolith.features.updater.Updater
import tv.twitch.android.shared.settings.SettingsTracker
import tv.twitch.android.shared.ui.menus.core.MenuAdapterBinder

class PurpleTVUpdaterSettingsPresenter(
    activity: FragmentActivity,
    adapterBinder: MenuAdapterBinder,
    settingsTracker: SettingsTracker,
    controller: PurpleTVSettingsController,
    factory: TwitchItemsFactory
) : BaseSettingsPresenter(
    activity,
    adapterBinder,
    settingsTracker,
    controller,
    PurpleTVSubMenu.OTA,
    factory
) {
    override fun updateSettingModels() {
        super.updateSettingModels()
        addInfoMenu(RES_STRINGS.purpletv_settings_check_update.fromResToString(), null) {
            Updater.get().onCheckUpdateClicked(activity)
        }
        addInfoMenu(RES_STRINGS.purpletv_settings_clear_update_cache.fromResToString(), null) {
            Updater.get().onClearCacheClicked(activity)
        }
    }
}