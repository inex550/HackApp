package ru.faaen.hackapp.features.wherego.data.repo

import ru.faaen.hackapp.core.network.api.ApiService
import ru.faaen.hackapp.features.wherego.data.models.LocationNet
import javax.inject.Inject

class WhereGoRepository @Inject constructor(
    private val apiService: ApiService
) {

    suspend fun loadPlaces(): List<LocationNet> {
        return apiService.loadLocations()
    }
}