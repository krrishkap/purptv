package tv.purple.monolith.features.settings.bridge.factory

import androidx.fragment.app.FragmentActivity
import com.google.common.collect.ImmutableMap
import dagger.android.AndroidInjector
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.internal.GeneratedComponent
import tv.purple.monolith.component.blacklist.Blacklist
import tv.purple.monolith.component.highlighter.Highlighter
import tv.purple.monolith.core.compat.ClassCompat.callPrivateMethod
import tv.purple.monolith.core.compat.ClassCompat.getPrivateField
import tv.purple.monolith.features.proxy.Proxy
import tv.purple.monolith.features.settings.bridge.DaggerInjectionList
import tv.purple.monolith.features.settings.bridge.IModFragment
import tv.purple.monolith.features.settings.bridge.settings.PurpleTVSettingsFragment
import tv.purple.monolith.features.settings.component.OrangeSettingsController
import tv.purple.monolith.features.settings.component.OrangeSettingsController_Factory
import tv.twitch.android.routing.routers.WebViewRouter
import tv.twitch.android.shared.settings.BaseSettingsPresenter
import tv.twitch.android.shared.settings.SettingsTracker
import tv.twitch.android.shared.ui.menus.core.MenuAdapterBinder
import javax.inject.Inject
import javax.inject.Provider

class PurpleTVSettingsDaggerFactory @Inject constructor(
    private val highlighterFeature: Highlighter,
    private val blacklistFeature: Blacklist,
    private val twitchItemsFactory: TwitchItemsFactory,
    private val proxy: Proxy,
    private val appComponent: GeneratedComponent
) {
    fun injectSubcomponentSettingsProvider(
        builderMap: ImmutableMap.Builder<Any, Any>,
        activityComponent: ActivityComponent
    ) {
        val orangeSettingsControllerProvider = provideOrangeSettingsController(
            activityComponent,
            appComponent
        )
        DaggerInjectionList.values().associate {
            it.fragment to buildInjector<PurpleTVSettingsFragment>(
                activityComponent,
                orangeSettingsControllerProvider,
                { twitchItemsFactory },
                it.presenter
            )
        }.forEach { map ->
            builderMap.put(map.key, map.value)
        }
    }

    private fun provideOrangeSettingsController(
        activityComponent: ActivityComponent,
        genComponent: GeneratedComponent
    ): Provider<OrangeSettingsController> {
        return Provider {
            OrangeSettingsController_Factory.newInstance(
                activityComponent.getPrivateField<Provider<FragmentActivity>>("provideFragmentActivityProvider")
                    .get(),
                callPrivateMethod(genComponent, "iFragmentRouter"),
                genComponent.getPrivateField<Provider<WebViewRouter>>("provideWebViewRouterProvider")
                    .get(),
                highlighterFeature,
                blacklistFeature,
                twitchItemsFactory,
                proxy
            )
        }
    }


    companion object {
        fun <T : IModFragment> buildInjector(
            activityComponent: ActivityComponent,
            settingsControllerProvider: Provider<OrangeSettingsController>,
            factory: Provider<TwitchItemsFactory>,
            clazz: Class<out BaseSettingsPresenter>
        ): Provider<AndroidInjector.Factory<*>> {
            return Provider<AndroidInjector.Factory<*>> {
                object : AbstractOrangeSettingsFragmentSubcomponentFactory<T>(
                    activityComponent.getPrivateField("provideFragmentActivityProvider"),
                    callPrivateMethod(activityComponent, "menuAdapterBinder"),
                    callPrivateMethod(activityComponent, "settingsTracker"),
                    settingsControllerProvider,
                    factory
                ) {
                    override fun providePresenter(
                        fragmentActivity: FragmentActivity,
                        adapterBinder: MenuAdapterBinder,
                        settingsTracker: SettingsTracker,
                        orangeSettingsController: OrangeSettingsController,
                        twitchItemsFactory: TwitchItemsFactory
                    ): BaseSettingsPresenter {
                        return clazz.constructors.first().newInstance(
                            fragmentActivity,
                            adapterBinder,
                            settingsTracker,
                            orangeSettingsController,
                            twitchItemsFactory
                        ) as BaseSettingsPresenter
                    }
                }
            }
        }
    }
}