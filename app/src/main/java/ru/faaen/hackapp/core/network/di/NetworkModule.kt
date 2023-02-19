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
import ru.faaen.hackapp.core.network.api.AuthApiService
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
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))

        return builder.build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        val rt = retrofit.newBuilder().baseUrl(BASE_URL).build()
        return rt.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideAuthApiService(retrofit: Retrofit): AuthApiService {
        val rt = retrofit.newBuilder().baseUrl(BASE_AUTH_URL).build()
        return rt.create(AuthApiService::class.java)
    }

    companion object {
        const val BASE_URL = "http://192.168.114.98:8000/"
        const val BASE_AUTH_URL = "http://192.168.114.98:8001/"

        const val OKHTTP_TAG = "OkHttp"
    }
}
