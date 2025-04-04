plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    namespace = "tv.twitch.android.app"
    compileSdk = 35
    defaultConfig {
        minSdk = 21
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = Config.jvmTarget
    }
}

dependencies {
    api(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    api(Dependencies.Appcompat)
    api(Dependencies.ConstraintLayout)
    api(Dependencies.RecyclerView)
    api(Dependencies.Material)

    api(Dependencies.RxJava)
    api(Dependencies.RxAndroid)

    api(Dependencies.Hilt)
    api(Dependencies.DaggerAndroid)

    api(Dependencies.Gson)

    api(Dependencies.Okhttp3)
    api(Dependencies.Okhttp3LoggingInterceptor)

    api(Dependencies.Retrofit)
    api(Dependencies.RetrofitGson)
    api(Dependencies.RetrofitRx)

    api(Dependencies.Glide)
    api(Dependencies.GlideOkHttp3)
    api(Dependencies.GlideWebp)

    api(Dependencies.WorkRuntime)
    api(Dependencies.WorkRxjava)

    api(Dependencies.RoomRxjava)

    api(Dependencies.ExoPlayer)
    api(Dependencies.ExoPlayerCore)

    api(Dependencies.Timber)

    api(Dependencies.AndroidSvg)

    api(Dependencies.Bugsnag) // Injected lib

    api(Dependencies.Relinker)

    api(Dependencies.Compose)
    api(Dependencies.ComposeFoundation)
    api(Dependencies.ComposeMaterial)
}