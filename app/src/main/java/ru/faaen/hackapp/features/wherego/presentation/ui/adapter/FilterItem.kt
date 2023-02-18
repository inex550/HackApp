package ru.faaen.hackapp.features.wherego.presentation.ui.adapter

import ru.faaen.hackapp.core.ui.recycler.BaseItem
import ru.faaen.hackapp.features.wherego.data.models.FilterOption
import ru.faaen.hackapp.features.wherego.data.models.Filters

data class FilterItem(
    val filter: Filters,
    var option: FilterOption? = null,
): BaseItem