package ru.faaen.hackapp.features.wherego.data.models

data class LocationNet(
    val name: String,
    val city: String,
    val street: String,
    val mealPlan: String,
    val coordinates: LatLngNet,
    val houseNumber: String,
    val minDays: String,
    val maxDays: String,
    val photos: List<String>,
)