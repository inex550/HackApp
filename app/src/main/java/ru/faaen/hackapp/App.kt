package ru.faaen.hackapp

import android.app.Application
import android.content.Context
import android.os.Build
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.decode.VideoFrameDecoder
import timber.log.Timber

class App: Application(), ImageLoaderFactory {

    override fun onCreate() {
        super.onCreate()

        app = this

        Timber.plant(Timber.DebugTree())
    }

    override fun newImageLoader(): ImageLoader {
        return ImageLoader.Builder(this)
            .crossfade(true)
            .components {
                if (Build.VERSION.SDK_INT > 28)
                    add(ImageDecoderDecoder.Factory())
                else
                    add(GifDecoder.Factory())
                add(VideoFrameDecoder.Factory())
            }
            .build()
    }

    companion object {
        lateinit var app: App private set

        val context: Context get() = app
    }
}