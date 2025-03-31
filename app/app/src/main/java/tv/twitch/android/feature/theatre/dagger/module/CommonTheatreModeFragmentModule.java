package tv.twitch.android.feature.theatre.dagger.module;

import javax.inject.Named;

import tv.purple.monolith.features.chat.ChatHookProvider;
import tv.twitch.android.shared.raids.RaidsConfiguration;

public final class CommonTheatreModeFragmentModule {
    /* ... */

    @Named
    public final RaidsConfiguration provideRaidsConfiguration() {
        return ChatHookProvider.hookRaidsConfiguration(null); // TODO: __INJECT_CODE
    }

    /* ... */
}
