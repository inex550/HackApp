package ru.faaen.hackapp.features.wherego.data.models

data class FilterOption(
    val name: String,
    val value: String? = null,
) {
    val valueOrName: String get() = value ?: name
}