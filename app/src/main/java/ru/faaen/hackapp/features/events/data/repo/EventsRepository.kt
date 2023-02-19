package ru.faaen.hackapp.features.events.data.repo

import kotlinx.coroutines.delay
import ru.faaen.hackapp.core.network.api.ApiService
import ru.faaen.hackapp.features.events.data.models.EventNet
import ru.faaen.hackapp.features.events.presentation.ui.adapter.EventsItem
import ru.faaen.hackapp.features.events.presentation.ui.adapter.FriendsInfo
import javax.inject.Inject

class EventsRepository @Inject constructor(
    private val apiService: ApiService
) {

    suspend fun loadEvents(): List<EventNet> {
        return apiService.loadEvents()
    }
}