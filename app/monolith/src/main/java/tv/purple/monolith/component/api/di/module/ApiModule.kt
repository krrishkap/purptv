package tv.purple.monolith.component.api.di.module

import dagger.Module
import dagger.Provides
import dagger.hilt.migration.DisableInstallInCheck
import dagger.internal.Provider
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import tv.purple.monolith.component.api.data.api.BttvApi
import tv.purple.monolith.component.api.data.api.Chatsen2Api
import tv.purple.monolith.component.api.data.api.ChatsenApi
import tv.purple.monolith.component.api.data.api.ChatterinoApi
import tv.purple.monolith.component.api.data.api.FFZAPApi
import tv.purple.monolith.component.api.data.api.FfzApi
import tv.purple.monolith.component.api.data.api.FlxrsApi
import tv.purple.monolith.component.api.data.api.HomiesApi
import tv.purple.monolith.component.api.data.api.NopApi
import tv.purple.monolith.component.api.data.api.PronounsApi
import tv.purple.monolith.component.api.data.api.StvApi
import tv.purple.monolith.component.api.data.api.StvOldApi
import tv.purple.monolith.component.api.data.api.TwitchGQLApi
import tv.purple.monolith.component.api.di.scope.BTTV
import tv.purple.monolith.component.api.di.scope.CHATSEN
import tv.purple.monolith.component.api.di.scope.CHATSEN2
import tv.purple.monolith.component.api.di.scope.CHATTERINO
import tv.purple.monolith.component.api.di.scope.FFZ
import tv.purple.monolith.component.api.di.scope.FFZAP
import tv.purple.monolith.component.api.di.scope.FLXRS
import tv.purple.monolith.component.api.di.scope.GQL
import tv.purple.monolith.component.api.di.scope.HOMIES
import tv.purple.monolith.component.api.di.scope.NOP
import tv.purple.monolith.component.api.di.scope.PRN
import tv.purple.monolith.component.api.di.scope.STV
import tv.purple.monolith.component.api.di.scope.STV_OLD_API
import tv.purple.monolith.core.compat.ClassCompat.getPrivateField
import tv.twitch.android.network.graphql.TwitchApolloClient
import javax.inject.Named
import javax.inject.Singleton

@DisableInstallInCheck
@Module(includes = [ProxyModule::class])
object ApiModule {
    @Singleton
    @Provides
    fun provideScheduler(): Scheduler {
        return Schedulers.io()
    }

    @Provides
    @Named(BTTV)
    fun provideBttvRetrofitClient(builder: Retrofit.Builder): Retrofit {
        return builder.baseUrl("https://api.betterttv.net/").build()
    }

    @Provides
    @Named(STV_OLD_API)
    fun provideStvOldApiRetrofitClient(builder: Retrofit.Builder): Retrofit {
        return builder.baseUrl("https://api.7tv.app/").build()
    }

    @Provides
    @Named(STV)
    fun provideStvRetrofitClient(builder: Retrofit.Builder): Retrofit {
        return builder.baseUrl("https://7tv.io/").build()
    }

    @Provides
    @Named(FFZ)
    fun provideFfzRetrofitClient(builder: Retrofit.Builder): Retrofit {
        return builder.baseUrl("https://api.frankerfacez.com/").build()
    }

    @Provides
    @Named(CHATTERINO)
    fun provideChatterinoRetrofitClient(builder: Retrofit.Builder): Retrofit {
        return builder.baseUrl("https://api.chatterino.com/").build()
    }

    @Provides
    @Named(NOP)
    fun provideNopRetrofitClient(builder: Retrofit.Builder): Retrofit {
        return builder.baseUrl("https://api.nopbreak.ru/").build()
    }

    @Provides
    @Named(FFZAP)
    fun provideFfzapRetrofitClient(builder: Retrofit.Builder): Retrofit {
        return builder.baseUrl("https://api.ffzap.com/").build()
    }

    @Provides
    @Named(PRN)
    fun providePronounsRetrofitClient(builder: Retrofit.Builder): Retrofit {
        return builder.baseUrl("https://pronouns.alejo.io/").build()
    }

    @Provides
    @Named(HOMIES)
    fun provideHomiesRetrofitClient(builder: Retrofit.Builder): Retrofit {
        return builder.baseUrl("https://itzalex.github.io/").build()
    }

    @Provides
    @Named(FLXRS)
    fun provideFlxrsRetrofitClient(builder: Retrofit.Builder): Retrofit {
        return builder.baseUrl("https://flxrs.com/").build()
    }

    @Provides
    @Named(CHATSEN)
    fun provideChatsenRetrofitClient(builder: Retrofit.Builder): Retrofit {
        return builder.baseUrl("https://raw.githubusercontent.com/").build()
    }

    @Provides
    @Named(CHATSEN2)
    fun provideChatsen2RetrofitClient(builder: Retrofit.Builder): Retrofit {
        return builder.baseUrl("https://api.chatsen.app/").build()
    }

    @Singleton
    @Provides
    @Named(GQL)
    fun provideTwitchGQLApiClient(
        builder: Retrofit.Builder,
        twitchApolloClient: Provider<TwitchApolloClient>
    ): Retrofit {
        return builder
            .baseUrl("https://gql.twitch.tv/gql/")
            .client(twitchApolloClient.get().getPrivateField<OkHttpClient>("injectedClient"))
            .build()
    }

    @Singleton
    @Provides
    fun provideBttvApi(@Named(BTTV) retrofit: Retrofit): BttvApi {
        return retrofit.create(BttvApi::class.java)
    }

    @Singleton
    @Provides
    fun provideStvApi(@Named(STV) retrofit: Retrofit): StvApi {
        return retrofit.create(StvApi::class.java)
    }

    @Singleton
    @Provides
    fun provideStvOldApi(@Named(STV_OLD_API) retrofit: Retrofit): StvOldApi {
        return retrofit.create(StvOldApi::class.java)
    }

    @Singleton
    @Provides
    fun provideFfzApi(@Named(FFZ) retrofit: Retrofit): FfzApi {
        return retrofit.create(FfzApi::class.java)
    }

    @Singleton
    @Provides
    fun provideChatterinoApi(@Named(CHATTERINO) retrofit: Retrofit): ChatterinoApi {
        return retrofit.create(ChatterinoApi::class.java)
    }

    @Singleton
    @Provides
    fun provideNopApi(@Named(NOP) retrofit: Retrofit): NopApi {
        return retrofit.create(NopApi::class.java)
    }

    @Singleton
    @Provides
    fun provideFfzapApi(@Named(FFZAP) retrofit: Retrofit): FFZAPApi {
        return retrofit.create(FFZAPApi::class.java)
    }

    @Singleton
    @Provides
    fun providePronounsApi(@Named(PRN) retrofit: Retrofit): PronounsApi {
        return retrofit.create(PronounsApi::class.java)
    }

    @Singleton
    @Provides
    fun provideTwitchGQLApi(@Named(GQL) retrofit: Retrofit): TwitchGQLApi {
        return retrofit.create(TwitchGQLApi::class.java)
    }

    @Singleton
    @Provides
    fun provideHomiesApi(@Named(HOMIES) retrofit: Retrofit): HomiesApi {
        return retrofit.create(HomiesApi::class.java)
    }

    @Singleton
    @Provides
    fun provideFlxrsApi(@Named(FLXRS) retrofit: Retrofit): FlxrsApi {
        return retrofit.create(FlxrsApi::class.java)
    }

    @Singleton
    @Provides
    fun provideChatsenApi(@Named(CHATSEN) retrofit: Retrofit): ChatsenApi {
        return retrofit.create(ChatsenApi::class.java)
    }

    @Singleton
    @Provides
    fun provideChatsen2Api(@Named(CHATSEN2) retrofit: Retrofit): Chatsen2Api {
        return retrofit.create(Chatsen2Api::class.java)
    }
}