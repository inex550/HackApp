package ru.faaen.hackapp.features.shop.vm

import ru.faaen.hackapp.core.ui.base.BaseViewState
import ru.faaen.hackapp.core.ui.recycler.BaseItem

data class ShopViewState(
    val balance: Int = 611,
    val items: List<BaseItem> = listOf(),
): BaseViewState