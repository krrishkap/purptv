import com.android.build.gradle.internal.tasks.factory.dependsOn

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

tasks.register<Copy>("copyAppDex") {
    dependsOn("mergeDexRelease")
    from(layout.buildDirectory.dir("/intermediates/dex/release/mergeDexRelease/"))
    include("**/*.dex")
    into("${project.rootDir}/dex/")
    rename { "app.dex" }
    doFirst {
        println("[monolith] Copying dex...")
    }
    doLast {
        println("[monolith] Done!")
    }
}

tasks.register("genDex").apply {
    dependsOn("mergeDexRelease")
}

tasks.named("genDex") {
    finalizedBy("copyAppDex")
}

android {
    namespace = "tv.orange"
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

    buildFeatures {
        dataBinding = true
    }

    sourceSets.named("main") {
        java.filter.exclude("**/*")
    }
}

tasks.configureEach {
    when (this.name) {
        "dexBuilderRelease" -> this.doFirst {
            copy {
                println("[monolith] Exclude R.java from building")
                from("${rootProject.projectDir}/app/R.jar")
                into("${rootProject.projectDir}/app/build/intermediates/compile_and_runtime_not_namespaced_r_class_jar/release")
            }
        }

        "mergeDexRelease" -> this.doFirst {
            copy {
                println("[monolith] Exclude R.java from building")
                from("${rootProject.projectDir}/app/R.jar")
                into("${rootProject.projectDir}/app/build/intermediates/compile_and_runtime_not_namespaced_r_class_jar/release")
            }
            delete(fileTree("${rootProject.projectDir}/app/build/intermediates/project_dex_archive/release/out") {
                println("[monolith] Exclude all *.jar's from building")
                include(
                    "**/*.jar"
                )
            })
            println("[monolith] Exclude Twitch module *.dex'es")
            delete(fileTree("${rootProject.projectDir}/Twitch/build/.transforms") { include("**/*.dex") })
            println("[monolith] Exclude external libs from building")
            delete(fileTree("${rootProject.projectDir}/app/build/intermediates/external_libs_dex") {
                include(
                    "**/*.dex"
                )
            })
        }

        // Comment this section for gen app dex'es
        "compileReleaseJavaWithJavac" -> this.doLast {
            val path = "${layout.buildDirectory.get()}/intermediates/javac/release/classes"
            println("--------> $path")
            delete(file(path))
            mkdir(path)
        }
    }
}

dependencies {
    api(project(":monolith"))

    api(Dependencies.Hilt)
    kapt(Dependencies.HiltCompiler)
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}

hilt {
    enableAggregatingTask = false
}

afterEvaluate {
    File("${project.rootDir}/dex").mkdirs()
}