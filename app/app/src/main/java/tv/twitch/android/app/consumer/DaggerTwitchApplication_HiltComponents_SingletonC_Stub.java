package tv.twitch.android.app.consumer;

import com.google.common.collect.ImmutableMap;

import java.util.Map;

import javax.inject.Provider;

import dagger.android.AndroidInjector;
import dagger.hilt.android.components.ActivityComponent;
import tv.purple.monolith.features.settings.bridge.PurpleTVSettingsHook;

public class DaggerTwitchApplication_HiltComponents_SingletonC_Stub {
    /* ... */

    private Map<Class<?>, Provider<AndroidInjector.Factory<?>>> mapOfClassOfAndProviderOfAndroidInjectorFactoryOf() {
        ImmutableMap.Builder result = null;
        PurpleTVSettingsHook.injectToAndroidInjectorFactory(result, (ActivityComponent) this); // TODO: __INJECT_CODE
        return result.build();
    }

    /* ... */
}
