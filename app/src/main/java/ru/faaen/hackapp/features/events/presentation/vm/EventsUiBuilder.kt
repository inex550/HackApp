package ru.faaen.hackapp.features.events.presentation.vm

import ru.faaen.hackapp.core.common.adapter.ShimmerItem
import ru.faaen.hackapp.core.ui.recycler.BaseItem
import ru.faaen.hackapp.features.events.data.models.EventNet
import ru.faaen.hackapp.features.events.presentation.ui.adapter.EventsItem
import ru.faaen.hackapp.features.wherego.data.models.Filters
import ru.faaen.hackapp.features.wherego.data.models.LocationNet
import ru.faaen.hackapp.features.wherego.presentation.ui.adapter.FilterDateItem
import ru.faaen.hackapp.features.wherego.presentation.ui.adapter.FilterItem
import ru.faaen.hackapp.features.wherego.presentation.ui.adapter.WherePlaceItem
import javax.inject.Inject

class EventsUiBuilder<T: BaseItem> @Inject constructor() {

    private val filters: List<BaseItem> = listOf(
        FilterItem(Filters.LOCALLY),
        FilterDateItem(),
        FilterItem(Filters.PLACING_TYPE),
        FilterItem(Filters.FEDERAL_DISTRICT),
        FilterItem(Filters.SUBJECT_RF),
        FilterItem(Filters.EDUCATIONAL_ORGANIZATION),
        FilterItem(Filters.NUTRITION),
        FilterItem(Filters.SORT_BY_RATING)
    )

    private val items: MutableList<BaseItem> = MutableList(3) { ShimmerItem }

    fun getFilters(): List<BaseItem> = filters.toList()

    fun getItems(): List<BaseItem> = items.toList()

    fun setItems(newEvents: List<T>) {
        items.addAll(newEvents)
    }

    fun setLocations(locations: List<LocationNet>) {
        val newItems = locations.map { WherePlaceItem.fromLocation(it) }
        items.addAll(newItems)
    }

    fun setEvents(events: List<EventNet>) {
        val newItems = events.map { EventsItem.fromEventNet(it) }
        items.addAll(newItems)
    }

    fun clearShimmer() {
        items.removeAll { it is ShimmerItem }
    }
}