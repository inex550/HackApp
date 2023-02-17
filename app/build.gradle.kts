import dependencies.Dependencies

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")

    id("kotlin-parcelize")

    id("com.google.dagger.hilt.android")

    id("kotlin-kapt")
}

android {
    compileSdk = 32

    defaultConfig {
        applicationId = "ru.faaen.hackapp"
        minSdk = 24
        targetSdk = 32
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
    implementation(dependencies.Dependencies.appCompat)
    implementation(dependencies.Dependencies.material)
    implementation(dependencies.Dependencies.constraintLayout)
    implementation(dependencies.Dependencies.swipeRefreshLayout)

    implementation(dependencies.Dependencies.coreKtx)
    implementation(dependencies.Dependencies.activityKtx)
    implementation(dependencies.Dependencies.fragmentKtx)
    implementation(dependencies.Dependencies.lifecycleRuntimeKtx)
    implementation(dependencies.Dependencies.viewModelKtx)

    implementation(dependencies.Dependencies.retrofit)
    implementation(dependencies.Dependencies.converterGson)

    implementation(dependencies.Dependencies.cicerone)

    implementation(dependencies.Dependencies.hilt)
    kapt(dependencies.Dependencies.hiltCompiler)

    implementation(dependencies.Dependencies.timber)

    implementation(dependencies.Dependencies.coil)
    implementation(dependencies.Dependencies.coilGif)
    implementation(dependencies.Dependencies.coilVideo)

    implementation(dependencies.Dependencies.loggingInterceptor)

    implementation(dependencies.Dependencies.shimmer)
}