import dependencies.Dependencies

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")

    id("kotlin-parcelize")

    id("com.google.dagger.hilt.android")

    id("kotlin-kapt")
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
}

android {
    compileSdk = 33

    defaultConfig {
        applicationId = "ru.faaen.hackapp"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(Dependencies.appCompat)
    implementation(Dependencies.material)
    implementation(Dependencies.constraintLayout)
    implementation(Dependencies.swipeRefreshLayout)

    implementation(Dependencies.coreKtx)
    implementation(Dependencies.activityKtx)
    implementation(Dependencies.fragmentKtx)
    implementation(Dependencies.lifecycleRuntimeKtx)
    implementation(Dependencies.viewModelKtx)

    implementation(Dependencies.retrofit)
    implementation(Dependencies.converterGson)

    implementation(Dependencies.cicerone)

    implementation(Dependencies.hilt)
    implementation("com.google.android.gms:play-services-maps:18.0.2")
    implementation("androidx.appcompat:appcompat:1.4.1")
    kapt(Dependencies.hiltCompiler)

    implementation(Dependencies.timber)

    implementation(Dependencies.coil)
    implementation(Dependencies.coilGif)
    implementation(Dependencies.coilVideo)

    implementation(Dependencies.loggingInterceptor)

    implementation(Dependencies.shimmer)
}