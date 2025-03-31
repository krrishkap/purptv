package tv.purple.monolith.features.settings.bridge.factory

import tv.twitch.android.models.settings.SettingsDestination
import tv.twitch.android.shared.ui.menus.core.MenuModel
import tv.twitch.android.shared.ui.menus.infomenu.InfoMenuModel
import tv.twitch.android.shared.ui.menus.subscription.SubMenuModel
import javax.inject.Inject

@Suppress("CAST_NEVER_SUCCEEDS")
class TwitchItemsFactory @Inject constructor() {
    fun createSubMenuModel(title: String, desc: String?, dest: SettingsDestination): MenuModel {
        return SubMenuModel(title, desc, null, dest, true) as MenuModel
    }

    fun createSubMenuModel(title: String, dest: SettingsDestination): MenuModel {
        return createSubMenuModel(title = title, desc = null, dest = dest)
    }

    fun createInfoMenuModel(title: String, desc: String?, func: () -> Unit): MenuModel {
        return InfoMenuModel(title, desc, null, null, null, null) { func.invoke() } as MenuModel
    }
}