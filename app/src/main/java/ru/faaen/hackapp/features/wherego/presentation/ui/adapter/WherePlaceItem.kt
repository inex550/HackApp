package ru.faaen.hackapp.features.wherego.presentation.ui.adapter

import ru.faaen.hackapp.core.ui.recycler.BaseItem
import ru.faaen.hackapp.features.wherego.data.models.LocationNet

data class WherePlaceItem(
    val id: String,
    val image: String?,
    val name: String,
    val city: String,
    val countOptions: Int,
    val seatCost: String,
    val stayDuration: String,
    var isLiked: Boolean = false,
): BaseItem {

    companion object {
        fun fromLocation(location: LocationNet): WherePlaceItem = WherePlaceItem(
            id = location.name,
            image = location.photos.firstOrNull(),
            name = location.name,
            city = location.city,
            countOptions = 5,
            seatCost = "от 50₽",
            stayDuration = "от ${location.minDays} до ${location.maxDays} дней",
            isLiked = false
        )
    }
}