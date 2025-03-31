package tv.purple.monolith.features.settings.bridge.settings.submenu

import androidx.fragment.app.FragmentActivity
import tv.purple.monolith.features.settings.bridge.factory.TwitchItemsFactory
import tv.purple.monolith.features.settings.bridge.model.OrangeSubMenu
import tv.purple.monolith.features.settings.bridge.settings.BaseSettingsPresenter
import tv.purple.monolith.features.settings.component.OrangeSettingsController
import tv.twitch.android.shared.settings.SettingsTracker
import tv.twitch.android.shared.ui.menus.core.MenuAdapterBinder

class PurpleTVThirdPartyEmotesSettingsPresenter(
    activity: FragmentActivity,
    adapterBinder: MenuAdapterBinder,
    settingsTracker: SettingsTracker,
    controller: OrangeSettingsController,
    factory: TwitchItemsFactory
) : BaseSettingsPresenter(
    activity,
    adapterBinder,
    settingsTracker,
    controller,
    OrangeSubMenu.ThirdPartyEmotes,
    factory
)