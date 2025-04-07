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
import tv.purple.monolith.component.api.di.scope.ApiEndpoints
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
import tv.purple.monolith.core.util.NetUtil.createApi
import tv.purple.monolith.core.util.NetUtil.createRetrofit
import tv.twitch.android.network.graphql.TwitchApolloClient
import javax.inject.Named
import javax.inject.Singleton

@DisableInstallInCheck
@Module(includes = [ProxyModule::class])
object ApiModule {
    @Singleton
    @Provides
    fun provideScheduler(): Scheduler = Schedulers.io()

    @Provides
    @Named(BTTV)
    fun provideBttvRetrofitClient(
        builder: Retrofit.Builder
    ): Retrofit = createRetrofit(builder, ApiEndpoints.BTTV)

    @Provides
    @Named(STV_OLD_API)
    fun provideStvOldApiRetrofitClient(
        builder: Retrofit.Builder
    ): Retrofit = createRetrofit(builder, ApiEndpoints.STV_OLD_API)

    @Provides
    @Named(STV)
    fun provideStvRetrofitClient(
        builder: Retrofit.Builder
    ): Retrofit = createRetrofit(builder, ApiEndpoints.STV)

    @Provides
    @Named(FFZ)
    fun provideFfzRetrofitClient(
        builder: Retrofit.Builder
    ): Retrofit = createRetrofit(builder, ApiEndpoints.FFZ)

    @Provides
    @Named(CHATTERINO)
    fun provideChatterinoRetrofitClient(
        builder: Retrofit.Builder
    ): Retrofit = createRetrofit(builder, ApiEndpoints.CHATTERINO)

    @Provides
    @Named(NOP)
    fun provideNopRetrofitClient(
        builder: Retrofit.Builder
    ): Retrofit = createRetrofit(builder, ApiEndpoints.NOP)

    @Provides
    @Named(FFZAP)
    fun provideFfzapRetrofitClient(
        builder: Retrofit.Builder
    ): Retrofit = createRetrofit(builder, ApiEndpoints.FFZAP)

    @Provides
    @Named(PRN)
    fun providePronounsRetrofitClient(
        builder: Retrofit.Builder
    ): Retrofit = createRetrofit(builder, ApiEndpoints.PRONOUNS)

    @Provides
    @Named(HOMIES)
    fun provideHomiesRetrofitClient(
        builder: Retrofit.Builder
    ): Retrofit = createRetrofit(builder, ApiEndpoints.HOMIES)

    @Provides
    @Named(FLXRS)
    fun provideFlxrsRetrofitClient(
        builder: Retrofit.Builder
    ): Retrofit = createRetrofit(builder, ApiEndpoints.FLXRS)

    @Provides
    @Named(CHATSEN)
    fun provideChatsenRetrofitClient(
        builder: Retrofit.Builder
    ): Retrofit = createRetrofit(builder, ApiEndpoints.CHATSEN)

    @Provides
    @Named(CHATSEN2)
    fun provideChatsen2RetrofitClient(
        builder: Retrofit.Builder
    ): Retrofit = createRetrofit(builder, ApiEndpoints.CHATSEN2)

    @Singleton
    @Provides
    @Named(GQL)
    fun provideTwitchGQLApiClient(
        builder: Retrofit.Builder,
        twitchApolloClient: Provider<TwitchApolloClient>
    ): Retrofit {
        return builder
            .baseUrl(ApiEndpoints.TWITCH_GQL)
            .client(twitchApolloClient.get().getPrivateField<OkHttpClient>("injectedClient"))
            .build()
    }

    @Singleton
    @Provides
    fun provideBttvApi(
        @Named(BTTV) retrofit: Retrofit
    ): BttvApi = createApi(retrofit)

    @Singleton
    @Provides
    fun provideStvApi(
        @Named(STV) retrofit: Retrofit
    ): StvApi = createApi(retrofit)

    @Singleton
    @Provides
    fun provideStvOldApi(
        @Named(STV_OLD_API) retrofit: Retrofit
    ): StvOldApi = createApi(retrofit)

    @Singleton
    @Provides
    fun provideFfzApi(
        @Named(FFZ) retrofit: Retrofit
    ): FfzApi = createApi(retrofit)

    @Singleton
    @Provides
    fun provideChatterinoApi(
        @Named(CHATTERINO) retrofit: Retrofit
    ): ChatterinoApi = createApi(retrofit)

    @Singleton
    @Provides
    fun provideNopApi(
        @Named(NOP) retrofit: Retrofit
    ): NopApi = createApi(retrofit)

    @Singleton
    @Provides
    fun provideFfzapApi(
        @Named(FFZAP) retrofit: Retrofit
    ): FFZAPApi = createApi(retrofit)

    @Singleton
    @Provides
    fun providePronounsApi(
        @Named(PRN) retrofit: Retrofit
    ): PronounsApi = createApi(retrofit)

    @Singleton
    @Provides
    fun provideTwitchGQLApi(
        @Named(GQL) retrofit: Retrofit
    ): TwitchGQLApi = createApi(retrofit)

    @Singleton
    @Provides
    fun provideHomiesApi(
        @Named(HOMIES) retrofit: Retrofit
    ): HomiesApi = createApi(retrofit)

    @Singleton
    @Provides
    fun provideFlxrsApi(
        @Named(FLXRS) retrofit: Retrofit
    ): FlxrsApi = createApi(retrofit)

    @Singleton
    @Provides
    fun provideChatsenApi(
        @Named(CHATSEN) retrofit: Retrofit
    ): ChatsenApi = createApi(retrofit)

    @Singleton
    @Provides
    fun provideChatsen2Api(
        @Named(CHATSEN2) retrofit: Retrofit
    ): Chatsen2Api = createApi(retrofit)
}