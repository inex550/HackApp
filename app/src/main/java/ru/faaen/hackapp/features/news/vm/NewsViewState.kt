package ru.faaen.hackapp.features.news.vm

import ru.faaen.hackapp.core.ui.base.BaseViewState
import ru.faaen.hackapp.core.ui.recycler.BaseItem

data class NewsViewState(
    val filters: List<BaseItem>,
    val items: List<BaseItem>,
): BaseViewState