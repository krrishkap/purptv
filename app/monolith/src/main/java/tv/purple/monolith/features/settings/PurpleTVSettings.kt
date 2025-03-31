package tv.purple.monolith.features.settings

import com.google.common.collect.ImmutableMap
import dagger.hilt.android.components.ActivityComponent
import tv.purple.monolith.bridge.RES_STRINGS
import tv.purple.monolith.core.ResManager.fromResToString
import tv.purple.monolith.features.settings.bridge.factory.PurpleTVSettingsDaggerFactory
import tv.purple.monolith.features.settings.bridge.factory.TwitchItemsFactory
import tv.twitch.android.models.settings.SettingsDestination
import tv.twitch.android.shared.ui.menus.core.MenuModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PurpleTVSettings @Inject constructor(
    private val daggerFactory: PurpleTVSettingsDaggerFactory,
    private val twitchItemsFactory: TwitchItemsFactory,
) {
    fun inject(
        builder: ImmutableMap.Builder<Any, Any>,
        activityComponent: ActivityComponent
    ) {
        daggerFactory.injectSubcomponentSettingsProvider(
            builderMap = builder,
            activityComponent = activityComponent
        )
    }

    fun createPurpleTVMenuModel(): MenuModel {
        return twitchItemsFactory.createSubMenuModel(
            title = RES_STRINGS.purpletv_settings.fromResToString(),
            dest = SettingsDestination.PurpleSettings
        )
    }
}