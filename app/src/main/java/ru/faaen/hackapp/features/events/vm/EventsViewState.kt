package ru.faaen.hackapp.features.events.vm

import ru.faaen.hackapp.core.ui.recycler.BaseItem

data class EventsViewState(
    val filters: List<BaseItem>,
    val items: List<BaseItem>,
)
