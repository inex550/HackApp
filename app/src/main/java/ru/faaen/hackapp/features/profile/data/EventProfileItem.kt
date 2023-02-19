package ru.faaen.hackapp.features.profile.data

import ru.faaen.hackapp.core.ui.recycler.BaseItem


data class EventProfileItem(
    val id: String,
    var title: String,
    var data: String,
    var countPerson: Int,
    var sum: String,
): BaseItem


var EventProfiles = listOf(
    EventProfileItem(
        "1",
        "Презентация ассоциации технических клубов Самарского университета",
        "29.05.2022 - 29.05.2024",
        1,
        "От 0 ₽ "
    ),
    EventProfileItem(
        "2",
        "Презентация ассоциации технических клубов Самарского университета",
        "20.05.2022 - 20.05.2024",
        1,
        "От 0 ₽ "
    ),
    EventProfileItem(
        "3",
        "Презентация ассоциации технических клубов Самарского университета",
        "15.05.2022 - 15.05.2024",
        1,
        "От 0 ₽ "
    ),
    EventProfileItem(
        "4",
        "Презентация ассоциации технических клубов Самарского университета",
        "29.05.2022 - 29.05.2024",
        1,
        "От 0 ₽ "
    ),
    EventProfileItem(
        "5",
        "Презентация ассоциации технических клубов Самарского университета",
        "12.07.2022 - 12.07.2024",
        1,
        "От 0 ₽ "
    )
)

