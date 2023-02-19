package ru.faaen.hackapp.features.events.data.models

import ru.faaen.hackapp.features.events.presentation.ui.adapter.EventTypeNet

data class EventDetailsNet(
    val name: String,
    val link: String,
    val price: String,
    val description: String,
    val video: List<String>,
    val photos: List<String>,
    val type: EventTypeNet,
    val WoS: String,
)