package ru.faaen.hackapp.features.wherego.presentation.vm

import ru.faaen.hackapp.features.wherego.data.models.Filters
import ru.faaen.hackapp.features.wherego.presentation.ui.adapter.FilterItem
import ru.faaen.hackapp.features.wherego.presentation.ui.adapter.WherePlaceItem
import javax.inject.Inject

class WhereGoUiBuilder @Inject constructor() {
    private val filters: List<FilterItem> = listOf(
        FilterItem(Filters.FEDERAL_DISTRICT),
        FilterItem(Filters.SUBJECT_RF),
        FilterItem(Filters.LOCALLY),
        FilterItem(Filters.EDUCATIONAL_ORGANIZATION),
        FilterItem(Filters.PLACING_TYPE),
        FilterItem(Filters.NUTRITION),
        FilterItem(Filters.SORT_BY_RATING)
    )

    private val items: MutableList<WherePlaceItem> = MutableList(3) { pos ->
        WherePlaceItem(
            id = pos.toString(),
            image = "https://stud-files.sabir.pro/files/PtA4pFzxry-e6e200f4363190c4400ba0dba4958a16b719f4e33bc5e14f50e4ff6fdf8b871c.jpg",
            name = "Студенческое общежитие ПВГУС",
            city = "Тольяти",
            countOptions = 2,
            seatCost = "от 30₽",
            stayDuration = "от 2 до 30 дней"
        )
    }

    fun getFilters(): List<FilterItem> = filters.toList()

    fun getItems(): List<WherePlaceItem> = items.toList()
}