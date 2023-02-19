package ru.faaen.hackapp.features.auth.data.models

data class RegisterArgs(
    val username: String,
    val email: String,
    val password: String,
)
