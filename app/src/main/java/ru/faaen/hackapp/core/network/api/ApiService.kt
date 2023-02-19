package ru.faaen.hackapp.core.network.api

import retrofit2.http.GET
import retrofit2.http.Query
import ru.faaen.hackapp.features.events.data.models.EventNet
import ru.faaen.hackapp.features.wherego.data.models.LocationNet

interface ApiService {

    @GET("locations")
    suspend fun loadLocations(
        @Query("skip") skip: Int = 0,
        @Query("limit") limit: Int = 100,
    ): List<LocationNet>

    @GET("events")
    suspend fun loadEvents(
        @Query("skip") skip: Int = 0,
        @Query("limit") limit: Int = 100,
    ): List<EventNet>
}
