package ru.faaen.hackapp.features.events.presentation.ui.adapter

import com.google.gson.annotations.SerializedName

enum class EventTypeNet {
    @SerializedName("scientific")
    SCIENTIFIC,

    @SerializedName("cultural")
    CULTURAL,

    @SerializedName("proforientation")
    PROFORIENTATION,
}