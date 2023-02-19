package ru.faaen.hackapp.features.events.data.models

data class EventNet(
    val dateFrom: String,
    val dateTo: String,
    val details: EventDetailsNet,
)