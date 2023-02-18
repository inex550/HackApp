package ru.faaen.hackapp.features.wherego.presentation.vm

import ru.faaen.hackapp.core.ui.base.BaseViewState
import ru.faaen.hackapp.core.ui.recycler.BaseItem

data class WhereGoViewState(
    val filters: List<BaseItem> = listOf(),
    val items: List<BaseItem> = listOf(),
): BaseViewState
