plugins {
    id("com.android.library")
    id("kotlin-kapt")
    kotlin("android")
}

android {
    namespace = "tv.purple.monolith"
    compileSdk = 35
    defaultConfig {
        minSdk = 21
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
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
    kapt(Dependencies.HiltCompiler)
    kapt(Dependencies.RoomCompiler)
    api(Dependencies.Bugsnag)

    api(project(":Twitch"))
}
