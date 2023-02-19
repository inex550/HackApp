package ru.faaen.hackapp.core.network.api

import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query
import ru.faaen.hackapp.features.auth.data.models.RegisterArgs
import ru.faaen.hackapp.features.auth.data.models.UserNet

interface AuthApiService {

    @POST("login")
    suspend fun login(
        @Query("email") email: String,
        @Query("password") password: String,
    ): UserNet

    @POST("register")
    suspend fun register(
        @Body body: RegisterArgs
    ): UserNet
}