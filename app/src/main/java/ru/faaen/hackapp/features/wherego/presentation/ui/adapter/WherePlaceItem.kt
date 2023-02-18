package ru.faaen.hackapp.features.wherego.presentation.ui.adapter

import ru.faaen.hackapp.core.ui.recycler.BaseItem

data class WherePlaceItem(
    val id: String,
    val image: String,
    val name: String,
    val city: String,
    val countOptions: Int,
    val seatCost: String,
    val stayDuration: String,
    var isLiked: Boolean = false,
): BaseItem
