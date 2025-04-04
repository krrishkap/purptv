package tv.purple.monolith.features.settings.bridge.settings

import androidx.fragment.app.FragmentActivity
import tv.purple.monolith.features.settings.bridge.factory.TwitchItemsFactory
import tv.purple.monolith.features.settings.bridge.model.PurpleTVSubMenu
import tv.purple.monolith.features.settings.component.PurpleTVSettingsController
import tv.twitch.android.shared.settings.SettingsTracker
import tv.twitch.android.shared.ui.menus.core.MenuAdapterBinder

class PurpleTVChatSettingsPresenter(
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
    PurpleTVSubMenu.Chat,
    factory
)