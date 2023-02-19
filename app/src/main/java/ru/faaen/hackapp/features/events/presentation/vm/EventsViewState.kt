package ru.faaen.hackapp.features.events.presentation.vm

import ru.faaen.hackapp.core.ui.base.BaseViewState
import ru.faaen.hackapp.core.ui.recycler.BaseItem

data class EventsViewState(
    val filters: List<BaseItem>,
    val items: List<BaseItem>,
): BaseViewState