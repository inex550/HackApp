package ru.faaen.hackapp.features.shop.vm

import ru.faaen.hackapp.core.common.adapter.ShimmerAdapter
import ru.faaen.hackapp.core.common.adapter.ShimmerItem
import ru.faaen.hackapp.core.ui.recycler.BaseItem
import ru.faaen.hackapp.features.shop.ui.adapter.ProductItem
import javax.inject.Inject

class ShopUiBuilder @Inject constructor() {

    private val items: MutableList<BaseItem> = MutableList(3) { ShimmerItem }

    fun getItems(): List<BaseItem> = items.toList()

    fun setProducts() {
        val products: List<BaseItem> = listOf(
            ProductItem(
                id = "0",
                image = "https://i.ibb.co/0mhYRdB/Screen-Shot-2023-02-18-at-10-06-1.png",
                name = "Блокнот А5 на пружина",
                price = 75
            ),
        )

        items.addAll(products)
    }

    fun clearShimmer() {
        items.removeAll { it is ShimmerItem }
    }
}