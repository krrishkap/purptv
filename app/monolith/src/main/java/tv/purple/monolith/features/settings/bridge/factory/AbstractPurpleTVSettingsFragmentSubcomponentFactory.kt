package tv.purple.monolith.features.settings.bridge.factory

import androidx.fragment.app.FragmentActivity
import dagger.android.AndroidInjector
import tv.purple.monolith.features.settings.bridge.IModFragment
import tv.purple.monolith.features.settings.component.PurpleTVSettingsController
import tv.twitch.android.shared.settings.BaseSettingsPresenter
import tv.twitch.android.shared.settings.SettingsTracker
import tv.twitch.android.shared.ui.menus.core.MenuAdapterBinder
import javax.inject.Provider

abstract class AbstractPurpleTVSettingsFragmentSubcomponentFactory<T : IModFragment>(
    private val fragmentActivityProvider: Provider<FragmentActivity>,
    private val menuAdapterBinderProvider: MenuAdapterBinder,
    private val settingsTrackerProvider: SettingsTracker,
    private val purpleTVSettingsControllerProvider: Provider<PurpleTVSettingsController>,
    private val twitchItemsFactoryProvider: Provider<TwitchItemsFactory>
) : AndroidInjector.Factory<T> {

    abstract fun providePresenter(
        fragmentActivity: FragmentActivity,
        adapterBinder: MenuAdapterBinder,
        settingsTracker: SettingsTracker,
        purpleTVSettingsController: PurpleTVSettingsController,
        twitchItemsFactory: TwitchItemsFactory
    ): BaseSettingsPresenter

    override fun create(fragment: T): AndroidInjector<T> {
        return AndroidInjector<T> {
            fragment.setPresenter(
                providePresenter(
                    fragmentActivityProvider.get(),
                    menuAdapterBinderProvider,
                    settingsTrackerProvider,
                    purpleTVSettingsControllerProvider.get(),
                    twitchItemsFactoryProvider.get()
                )
            )
        }
    }
}
