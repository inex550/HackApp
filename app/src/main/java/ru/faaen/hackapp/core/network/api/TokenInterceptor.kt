package ru.faaen.hackapp.core.network.api

import okhttp3.Interceptor
import okhttp3.Response
import ru.faaen.hackapp.core.prefs.PreferenceStorage
import javax.inject.Inject

class TokenInterceptor @Inject constructor(
    private val prefs: PreferenceStorage
): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()

        if (prefs.token != null) {
            request = request.newBuilder()
                .addHeader("Authorization", "Bearer ${prefs.token}")
                .build()
        }

        return chain.proceed(request)
    }
}