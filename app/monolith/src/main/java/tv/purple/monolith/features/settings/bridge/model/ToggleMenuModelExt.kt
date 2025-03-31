package tv.purple.monolith.features.settings.bridge.model

import tv.twitch.android.shared.ui.menus.SettingsPreferencesController
import tv.twitch.android.shared.ui.menus.togglemenu.ToggleMenuModel

open class ToggleMenuModelExt(
    primaryText: String,
    secondaryText: String? = null,
    auxiliaryText: String? = null,
    state: Boolean
) : ToggleMenuModel(
    primaryText,
    secondaryText.let {
        if (it.isNullOrBlank()) {
            null
        } else {
            it
        }
    },
    auxiliaryText.let {
        if (it.isNullOrBlank()) {
            null
        } else {
            it
        }
    },
    null,
    state,
    true,
    null,
    "",
    false,
    null,
    null,
    null,
    SettingsPreferencesController.SettingsPreference.AdTracking,
    null,
    null
)