package ru.faaen.hackapp.features.news.data.repo

import kotlinx.coroutines.delay
import ru.faaen.hackapp.features.events.presentation.ui.adapter.FriendsInfo
import ru.faaen.hackapp.features.news.presentation.adapter.NewsItem
import javax.inject.Inject

class NewsRepository @Inject constructor() {

    suspend fun loadNews(): List<NewsItem> {
        delay(1000)
        return List(3) { pos ->
            NewsItem(
                id = pos.toString(),
                image = "https://i.ibb.co/QbYc6bw/Screen-Shot-2023-02-18-at-4-46-1.png",
                name = "Студенты технических специальностей из 4 регионов России пройдут программу практической подготовки на одном из ведущих предприятий российской космической отрасли",
                tags = listOf(
                    "#Студтуризм",
                    "#СТУДТУРИЗМ2022",
                    "#ПрофориентационныйТрек",
                ),
                friendsInfo = FriendsInfo(
                    friendsCount = 4,
                    avatar1 = "https://img.freepik.com/free-photo/young-bearded-man-with-striped-shirt_273609-5677.jpg",
                    avatar2 = "https://images.pexels.com/photos/733872/pexels-photo-733872.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                    avatar3 = "https://img.freepik.com/premium-photo/young-handsome-man-with-beard-isolated-keeping-arms-crossed-frontal-position_1368-132662.jpg?w=360"
                )
            )
        }
    }
}