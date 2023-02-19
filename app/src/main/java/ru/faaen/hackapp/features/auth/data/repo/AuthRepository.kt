package ru.faaen.hackapp.features.auth.data.repo

import kotlinx.coroutines.delay
import retrofit2.http.POST
import ru.faaen.hackapp.core.network.api.AuthApiService
import ru.faaen.hackapp.features.auth.data.models.RegisterArgs
import ru.faaen.hackapp.features.auth.data.models.UserNet
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val apiService: AuthApiService,
) {
    suspend fun login(email: String, password: String): UserNet {
        return apiService.login(email, password)
    }

    suspend fun register(fio: String, email: String, password: String): UserNet {
        return apiService.register(RegisterArgs(fio, email, password))
    }
}