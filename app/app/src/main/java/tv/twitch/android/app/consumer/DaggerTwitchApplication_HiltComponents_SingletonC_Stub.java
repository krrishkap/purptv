package tv.twitch.android.app.consumer;

import com.google.common.collect.ImmutableMap;

import java.util.Map;

import dagger.android.AndroidInjector;
import dagger.hilt.android.components.ActivityComponent;
import dagger.internal.Provider;
import tv.purple.monolith.features.settings.bridge.PurpleTVSettingsHook;
import tv.twitch.android.core.data.source.DataUpdater;
import tv.twitch.android.shared.player.overlay.RxBottomPlayerControlOverlayPresenter;
import tv.twitch.android.shared.theatre.data.pub.model.TheatreCoordinatorRequest;

public class DaggerTwitchApplication_HiltComponents_SingletonC_Stub {
    /* ... */

    private Map<Class<?>, Provider<AndroidInjector.Factory<?>>> mapOfClassOfAndProviderOfAndroidInjectorFactoryOf() {
        ImmutableMap.Builder result = null;
        PurpleTVSettingsHook.injectToAndroidInjectorFactory(result, (ActivityComponent) this); // TODO: __INJECT_CODE
        return result.build();
    }

    private static final class FragmentCImpl {
        private Provider<DataUpdater<TheatreCoordinatorRequest>> provideTheatreCoordinatorUpdateUpdaterProvider;

        private final FragmentCImpl fragmentCImpl = null;

        public RxBottomPlayerControlOverlayPresenter rxBottomPlayerControlOverlayPresenter() {
            RxBottomPlayerControlOverlayPresenter presenter = null;
            presenter.inject(provideTheatreCoordinatorUpdateUpdaterProvider); // TODO: __INJECT_CODE

            return null;
        }

    }

    /* ... */
}
