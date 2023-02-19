package buildSrc.dependencies

object Dependencies {
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val swipeRefreshLayout = "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swipeRefreshLayout}"

    const val cameraCore = "androidx.camera:camera-core:${Versions.cameraX}"
    const val camera2 = "androidx.camera:camera-camera2:${Versions.cameraX}"
    const val cameraLifecycle = "androidx.camera:camera-lifecycle:${Versions.cameraX}"
    const val cameraVideo = "androidx.camera:camera-video:${Versions.cameraX}"
    const val cameraView = "androidx.camera:camera-view:${Versions.cameraX}"
    const val cameraExtensions = "androidx.camera:camera-extensions:${Versions.cameraX}"

    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    const val activityKtx = "androidx.activity:activity-ktx:${Versions.activityKtx}"
    const val fragmentKtx = "androidx.fragment:fragment-ktx:${Versions.fragmentKtx}"
    const val viewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.viewModelKtx}"
    const val lifecycleRuntimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycleRuntimeKtx}"

    const val firebaseBom = "com.google.firebase:firebase-bom:${Versions.firebaseBom}"
    const val firebaseMessaging = "com.google.firebase:firebase-messaging-ktx:${Versions.firebaseMessaging}"

    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val converterGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val cicerone = "com.github.terrakok:cicerone:${Versions.cicerone}"
    const val hilt = "com.google.dagger:hilt-android:${Versions.hilt}"
    const val hiltCompiler = "com.google.dagger:hilt-compiler:${Versions.hilt}"
    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
    const val coil = "io.coil-kt:coil:${Versions.coil}"
    const val coilGif = "io.coil-kt:coil-gif:${Versions.coil}"
    const val coilVideo = "io.coil-kt:coil-video:${Versions.coil}"
    const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.loggingInterceptor}"
    const val shimmer = "com.facebook.shimmer:shimmer:${Versions.shimmer}"
    const val room = "androidx.room:room-ktx:${Versions.room}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
    const val exoPlayer = "com.google.android.exoplayer:exoplayer:${Versions.exoPlayer}"
    const val blurKit = "io.alterac.blurkit:blurkit:${Versions.blurKit}"
    const val ffmpegMobile = "com.arthenica:mobile-ffmpeg-full:${Versions.ffmpegMobile}"
}
