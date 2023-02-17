package ru.faaen.hackapp.features.auth.data.repo

import kotlinx.coroutines.delay
import javax.inject.Inject

class AuthRepository @Inject constructor() {
    suspend fun login(email: String, password: String) {
        delay(1000)
    }

    suspend fun register(fio: String, email: String, password: String) {
        delay(1000)
    }
}