package ru.faaen.hackapp.features.shop.ui.adapter

import ru.faaen.hackapp.core.ui.recycler.BaseItem

data class ProductItem(
    val id: String,
    val image: String,
    val name: String,
    val price: Int,
): BaseItem