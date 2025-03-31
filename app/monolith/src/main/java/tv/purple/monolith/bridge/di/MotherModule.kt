package tv.purple.monolith.bridge.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.android.internal.managers.ApplicationComponentManager
import dagger.hilt.internal.GeneratedComponent
import dagger.hilt.migration.DisableInstallInCheck
import dagger.internal.Provider
import tv.purple.monolith.component.blacklist.db.BlacklistDatabase
import tv.purple.monolith.component.highlighter.db.HighlighterDatabase
import tv.purple.monolith.core.Config
import tv.purple.monolith.core.compat.ClassCompat.callPrivateMethod
import tv.purple.monolith.core.compat.ClassCompat.getPrivateField
import tv.purple.monolith.features.chat.db.FavEmotesDatabase
import tv.twitch.android.api.TwitchAccountManagerUpdater
import tv.twitch.android.app.consumer.Hilt_TwitchApplication
import tv.twitch.android.core.analytics.UniqueDeviceIdentifier
import tv.twitch.android.core.user.TwitchAccountManager
import tv.twitch.android.network.graphql.GraphQlService
import tv.twitch.android.network.graphql.TwitchApolloClient
import tv.twitch.android.shared.chat.settings.preferences.ChatSettingsPreferencesFile
import javax.inject.Singleton


@Module
@DisableInstallInCheck
class MotherModule {
    @Provides
    @Singleton
    fun provideConfig(): Config = Config

    @Provides
    @Singleton
    fun provideFavEmotesDatabase(): FavEmotesDatabase {
        return FavEmotesDatabase.INSTANCE
    }

    @Provides
    @Singleton
    fun provideBlacklistDatabase(context: Context): BlacklistDatabase {
        return BlacklistDatabase(context, "orange_blacklist")
    }

    @Provides
    @Singleton
    fun provideHighlighterDatabase(context: Context): HighlighterDatabase {
        return HighlighterDatabase(context, "orange_highlighter_v2")
    }

    @Provides
    fun provideApplicationComponentManager(context: Context): ApplicationComponentManager {
        if (context is Hilt_TwitchApplication) {
            return context.componentManager()
        }

        throw IllegalStateException("Context must provide ApplicationComponentManager")
    }

    @Provides
    fun provideSingletonCImpl(acm: ApplicationComponentManager): GeneratedComponent {
        val component = acm.generatedComponent()
        if (component is GeneratedComponent) {
            return component
        }

        throw IllegalStateException("Can't get GC!")
    }

    @Provides
    @Singleton
    fun provideTwitchAccountManager(gc: GeneratedComponent): TwitchAccountManager {
        val provider = gc.getPrivateField<Provider<TwitchAccountManager>?>(
            fieldName = "twitchAccountManagerProvider"
        ) ?: throw java.lang.IllegalStateException("twitchAccountManagerProvider not found!")

        return provider.get()
    }

    @Provides
    fun provideTwitchGraphQlService(gc: GeneratedComponent): GraphQlService {
        return callPrivateMethod(
            obj = gc,
            methodName = "graphQlService"
        ) as GraphQlService
    }

    @Provides
    fun provideUniqueDeviceIdentifierProvider(gc: GeneratedComponent): UniqueDeviceIdentifier {
        return gc.getPrivateField<Provider<UniqueDeviceIdentifier>>(
            fieldName = "uniqueDeviceIdentifierProvider"
        ).get()
    }

    @Provides
    fun provideTwitchApolloClient(gc: GeneratedComponent): Provider<TwitchApolloClient> {
        return gc.getPrivateField(
            fieldName = "provideApolloGqlOkHttpClientProvider"
        )
    }

    @Provides
    fun provideChatSettingsPreferencesFile(gc: GeneratedComponent): ChatSettingsPreferencesFile {
        return gc.getPrivateField<Provider<ChatSettingsPreferencesFile>>(
            fieldName = "chatSettingsPreferencesFileProvider"
        ).get()
    }

    @Provides
    fun provideTwitchAccountManagerUpdater(gc: GeneratedComponent): TwitchAccountManagerUpdater {
        return gc.getPrivateField<Provider<TwitchAccountManagerUpdater>>(
            fieldName = "twitchAccountManagerUpdaterProvider"
        ).get()
    }

}