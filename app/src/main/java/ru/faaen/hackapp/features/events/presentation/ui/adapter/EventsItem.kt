package ru.faaen.hackapp.features.events.presentation.ui.adapter

import ru.faaen.hackapp.App
import ru.faaen.hackapp.R
import ru.faaen.hackapp.core.ui.recycler.BaseItem
import ru.faaen.hackapp.features.events.data.models.EventNet
import kotlin.random.Random

data class EventsItem(
    val id: String,
    val image: String?,
    val name: String,
    val genre: String,
    val startDate: String,
    val endDate: String,
    val friendsInfo: FriendsInfo,
    var isLiked: Boolean = false,
): BaseItem {

    companion object {
        fun fromEventNet(event: EventNet): EventsItem = EventsItem(
            id = Random.nextInt(0, 100000).toString(),
            image = event.details.photos.firstOrNull(),
            name = event.details.name,
            genre = when (event.details.type) {
                EventTypeNet.SCIENTIFIC -> App.context.getString(R.string.scientific)
                EventTypeNet.CULTURAL -> App.context.getString(R.string.cultural)
                EventTypeNet.PROFORIENTATION -> App.context.getString(R.string.proforientation)
            },
            startDate = event.dateFrom,
            endDate = event.dateTo,
            friendsInfo = FriendsInfo(
                friendsCount = 3,
                avatar1 = "https://img.freepik.com/free-photo/young-bearded-man-with-striped-shirt_273609-5677.jpg",
                avatar2 = "https://img.freepik.com/premium-vector/portrait-of-a-young-man-with-beard-and-hair-style-male-avatar-vector-illustration_266660-423.jpg",
                avatar3 = "https://images.pexels.com/photos/733872/pexels-photo-733872.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"
            ),
            isLiked = false
        )
    }
}