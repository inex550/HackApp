package ru.faaen.hackapp.core.network.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.faaen.hackapp.BuildConfig
import ru.faaen.hackapp.core.network.api.ApiService
import ru.faaen.hackapp.core.network.api.TokenInterceptor
import ru.faaen.hackapp.core.network.api.HCCookieJar
import timber.log.Timber
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(
        tokenInterceptor: TokenInterceptor
    ): OkHttpClient {
        return OkHttpClient().newBuilder().run {
            if (BuildConfig.DEBUG) addInterceptor(
                HttpLoggingInterceptor {
                    Timber.tag(OKHTTP_TAG).d(it)
                }.setLevel(HttpLoggingInterceptor.Level.BODY)
            )
            addInterceptor(tokenInterceptor)
            cookieJar(HCCookieJar())
            build()
        }
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder()
            .setLenient()
            .create()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        client: OkHttpClient,
        gson: Gson
    ): Retrofit {
        val builder = Retrofit.Builder()
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(BASE_URL)

        return builder.build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    companion object {
        const val BASE_URL = ""

        const val OKHTTP_TAG = "OkHttp"
    }
}
