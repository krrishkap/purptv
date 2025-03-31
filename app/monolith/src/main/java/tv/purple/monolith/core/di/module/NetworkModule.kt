package tv.purple.monolith.core.di.module

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.migration.DisableInstallInCheck
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import tv.purple.monolith.core.factory.StringConverterFactory
import tv.purple.monolith.core.models.flag.Flag
import tv.purple.monolith.core.util.PurpleBuildConfigUtil
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@DisableInstallInCheck
class NetworkModule {
    private val httpInterceptor by lazy {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient().newBuilder()
            .readTimeout(DEFAULT_OKHTTP_TIMEOUT, TimeUnit.MILLISECONDS)
            .writeTimeout(DEFAULT_OKHTTP_TIMEOUT, TimeUnit.MILLISECONDS).apply {
                addNetworkInterceptor { chain ->
                    chain.proceed(
                        chain.request().newBuilder().header(
                            USER_AGENT_KEY,
                            PurpleBuildConfigUtil.userAgent
                        ).build()
                    )
                }
                if (Flag.OKHTTP_LOGGING.asBoolean()) {
                    addInterceptor(httpInterceptor)
                }
            }
            .retryOnConnectionFailure(true).build()
    }

    @Provides
    fun provideRetrofitBuilder(client: OkHttpClient): Retrofit.Builder {
        return Retrofit.Builder()
            .addConverterFactory(StringConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
            .client(client)
    }

    companion object {
        private const val USER_AGENT_KEY = "User-Agent"
        const val DEFAULT_OKHTTP_TIMEOUT = 5000L
    }
}