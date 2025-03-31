package tv.purple.monolith.features.settings.bridge

import androidx.fragment.app.Fragment
import com.google.common.collect.ImmutableMap
import dagger.hilt.android.components.ActivityComponent
import tv.purple.monolith.bridge.PurpleTVAppContainer
import tv.purple.monolith.core.ResManager.fromResToStringId
import tv.purple.monolith.core.util.PurpleBuildConfigUtil
import tv.purple.monolith.features.settings.bridge.settings.PurpleTVSettingsFragment
import tv.twitch.android.app.consumer.BuildCiDataImpl
import tv.twitch.android.app.core.ApplicationContext
import tv.twitch.android.core.strings.DateUtil
import tv.twitch.android.shared.ui.menus.core.MenuModel
import java.util.Date

object PurpleTVSettingsHook {
    @JvmStatic
    fun injectToAndroidInjectorFactory(
        builder: ImmutableMap.Builder<Any, Any>,
        activityComponent: ActivityComponent
    ) {
        PurpleTVAppContainer.getPurpleTVSettings().inject(builder, activityComponent)
    }

    @JvmStatic
    fun createSettingsFragment(): Fragment {
        return PurpleTVSettingsFragment()
    }

    @JvmStatic
    fun injectOrangeSettingsMenuModel(models: MutableList<MenuModel>) {
        val menu = PurpleTVAppContainer.getPurpleTVSettings().createPurpleTVMenuModel()
        models.add(menu)
    }

    @JvmStatic
    fun getPurpleTVSettingsStringId(): Int {
        return "purpletv_settings".fromResToStringId()
    }

    @JvmStatic
    fun hookAppVersionString(appVersionString: String): String {
        return PurpleBuildConfigUtil.buildConfigVariant.let { config ->
            if (config.number > 0) {
                "${config.number}r${config.revision} [${
                    BuildCiDataImpl.INSTANCE.gitInfo.split(
                        '/',
                        limit = 2
                    ).last()
                }]"
            } else {
                "Debug"
            }
        }
    }

    @JvmStatic
    fun hookAppBuildDateString(appBuildDateString: String): String {
        return PurpleBuildConfigUtil.buildConfigVariant.let { config ->
            DateUtil.Companion!!.localizedDate(
                ApplicationContext.getInstance().getContext(), if (config.timestamp > 0) {
                    config.timestampToDate()
                } else {
                    Date(0)
                }
            )
        }
    }
}