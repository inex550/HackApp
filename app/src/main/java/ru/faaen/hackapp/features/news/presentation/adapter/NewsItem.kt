package ru.faaen.hackapp.features.news.presentation.adapter

import ru.faaen.hackapp.core.ui.recycler.BaseItem
import ru.faaen.hackapp.features.events.presentation.ui.adapter.FriendsInfo

data class NewsItem(
    val id: String,
    val image: String,
    val name: String,
    val tags: List<String>,
    val friendsInfo: FriendsInfo,
    var isLiked: Boolean = false,
): BaseItem