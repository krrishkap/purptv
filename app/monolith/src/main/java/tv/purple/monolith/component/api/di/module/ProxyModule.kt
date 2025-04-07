package tv.purple.monolith.component.api.di.module

import dagger.Module
import dagger.Provides
import dagger.hilt.migration.DisableInstallInCheck
import retrofit2.Retrofit
import tv.purple.monolith.component.api.data.api.proxy.LuminousASApi
import tv.purple.monolith.component.api.data.api.proxy.LuminousEU2Api
import tv.purple.monolith.component.api.data.api.proxy.LuminousEUApi
import tv.purple.monolith.component.api.data.api.proxy.PerfprodApi
import tv.purple.monolith.component.api.di.scope.ApiEndpoints
import tv.purple.monolith.component.api.di.scope.L_AS
import tv.purple.monolith.component.api.di.scope.L_EU
import tv.purple.monolith.component.api.di.scope.L_EU2
import tv.purple.monolith.component.api.di.scope.PP_AS
import tv.purple.monolith.component.api.di.scope.PP_EU
import tv.purple.monolith.component.api.di.scope.PP_EU2
import tv.purple.monolith.component.api.di.scope.PP_EU3
import tv.purple.monolith.component.api.di.scope.PP_EU4
import tv.purple.monolith.component.api.di.scope.PP_EU5
import tv.purple.monolith.component.api.di.scope.PP_NA
import tv.purple.monolith.component.api.di.scope.PP_SA
import tv.purple.monolith.core.util.NetUtil.createApi
import tv.purple.monolith.core.util.NetUtil.createRetrofit
import javax.inject.Named
import javax.inject.Singleton

@DisableInstallInCheck
@Module
object ProxyModule {
    @Provides
    @Named(L_EU)
    fun provideLEURetrofitClient(
        builder: Retrofit.Builder
    ): Retrofit = createRetrofit(builder, ApiEndpoints.Luminous.EU)

    @Provides
    @Named(L_EU2)
    fun provideLEU2RetrofitClient(
        builder: Retrofit.Builder
    ): Retrofit = createRetrofit(builder, ApiEndpoints.Luminous.EU2)

    @Provides
    @Named(L_AS)
    fun provideLASRetrofitClient(
        builder: Retrofit.Builder
    ): Retrofit = createRetrofit(builder, ApiEndpoints.Luminous.AS)

    @Provides
    @Named(PP_EU)
    fun providePP_EURetrofitClient(
        builder: Retrofit.Builder
    ): Retrofit = createRetrofit(builder, ApiEndpoints.PerfProd.EU)

    @Provides
    @Named(PP_EU2)
    fun providePP_EU2RetrofitClient(
        builder: Retrofit.Builder
    ): Retrofit = createRetrofit(builder, ApiEndpoints.PerfProd.EU2)

    @Provides
    @Named(PP_EU3)
    fun providePP_EU3RetrofitClient(
        builder: Retrofit.Builder
    ): Retrofit = createRetrofit(builder, ApiEndpoints.PerfProd.EU3)

    @Provides
    @Named(PP_EU4)
    fun providePP_EU4RetrofitClient(
        builder: Retrofit.Builder
    ): Retrofit = createRetrofit(builder, ApiEndpoints.PerfProd.EU4)

    @Provides
    @Named(PP_EU5)
    fun providePP_EU5RetrofitClient(
        builder: Retrofit.Builder
    ): Retrofit = createRetrofit(builder, ApiEndpoints.PerfProd.EU5)

    @Provides
    @Named(PP_NA)
    fun providePP_NARetrofitClient(
        builder: Retrofit.Builder
    ): Retrofit = createRetrofit(builder, ApiEndpoints.PerfProd.NA)

    @Provides
    @Named(PP_AS)
    fun providePP_ASRetrofitClient(
        builder: Retrofit.Builder
    ): Retrofit = createRetrofit(builder, ApiEndpoints.PerfProd.AS)

    @Provides
    @Named(PP_SA)
    fun providePP_SARetrofitClient(
        builder: Retrofit.Builder
    ): Retrofit = createRetrofit(builder, ApiEndpoints.PerfProd.SA)

    @Singleton
    @Provides
    fun provideLEUApi(
        @Named(L_EU) retrofit: Retrofit
    ): LuminousEUApi = createApi(retrofit)

    @Singleton
    @Provides
    fun provideLEU2Api(
        @Named(L_EU2) retrofit: Retrofit
    ): LuminousEU2Api = createApi(retrofit)

    @Singleton
    @Provides
    fun provideLASApi(
        @Named(L_AS) retrofit: Retrofit
    ): LuminousASApi = createApi(retrofit)

    @Singleton
    @Provides
    fun providePP_EU(
        @Named(PP_EU) retrofit: Retrofit
    ): PerfprodApi = createApi(retrofit)

    @Singleton
    @Provides
    fun providePP_EU2(
        @Named(PP_EU2) retrofit: Retrofit
    ): PerfprodApi = createApi(retrofit)

    @Singleton
    @Provides
    fun providePP_EU3(
        @Named(PP_EU3) retrofit: Retrofit
    ): PerfprodApi = createApi(retrofit)

    @Singleton
    @Provides
    fun providePP_EU4(
        @Named(PP_EU4) retrofit: Retrofit
    ): PerfprodApi = createApi(retrofit)

    @Singleton
    @Provides
    fun providePP_EU5(
        @Named(PP_EU5) retrofit: Retrofit
    ): PerfprodApi = createApi(retrofit)

    @Singleton
    @Provides
    fun providePP_NA(
        @Named(PP_NA) retrofit: Retrofit
    ): PerfprodApi = createApi(retrofit)

    @Singleton
    @Provides
    fun providePP_AS(
        @Named(PP_AS) retrofit: Retrofit
    ): PerfprodApi = createApi(retrofit)

    @Singleton
    @Provides
    fun providePP_SA(
        @Named(PP_SA) retrofit: Retrofit
    ): PerfprodApi = createApi(retrofit)
}