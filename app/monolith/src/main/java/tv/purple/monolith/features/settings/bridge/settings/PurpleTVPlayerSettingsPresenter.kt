package tv.purple.monolith.features.settings.bridge.settings

import androidx.fragment.app.FragmentActivity
import tv.purple.monolith.bridge.PurpleTVAppContainer
import tv.purple.monolith.core.models.flag.Flag
import tv.purple.monolith.features.settings.bridge.factory.TwitchItemsFactory
import tv.purple.monolith.features.settings.bridge.model.Item
import tv.purple.monolith.features.settings.bridge.model.PurpleTVSubMenu
import tv.purple.monolith.features.settings.component.PurpleTVSettingsController
import tv.twitch.android.shared.settings.SettingsTracker
import tv.twitch.android.shared.ui.menus.core.MenuAdapterBinder

class PurpleTVPlayerSettingsPresenter(
    activity: FragmentActivity,
    adapterBinder: MenuAdapterBinder,
    settingsTracker: SettingsTracker,
    controller: PurpleTVSettingsController,
    factory: TwitchItemsFactory,
) : BaseSettingsPresenter(
    activity,
    adapterBinder,
    settingsTracker,
    controller = controller,
    subMenuWrapper = PurpleTVSubMenu.Player,
    factory = factory,
    itemFilter = {
        if (it is Item.FlagItem && it.flag == Flag.VODHUNTER) {
            PurpleTVAppContainer.getInstance().provideVODHunter().canUseVodHunter() // DI?
        } else {
            true
        }
    }
)