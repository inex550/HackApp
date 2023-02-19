package ru.faaen.hackapp.features.profile.data

import ru.faaen.hackapp.core.ui.recycler.BaseItem


data class LifeProfileItem(
    val id: String,
    var title: String,
    var data: String,
    var countPerson: Int,
    var sum: String,
): BaseItem


var LifeProfiles = listOf(
    LifeProfileItem(
        "1",
        "Студенческое общежитие ПВГУС",
        "29.05.2022 - 29.05.2024",
        1,
        "От 0 ₽ "
    ),
    LifeProfileItem(
        "2",
        "Спортивно-оздоровительный лагерь <Горизонт>",
        "25.06.2022 - 25.06.2024",
        1,
        "От 0 ₽ "
    ),
    LifeProfileItem(
        "3",
        "Студенческое общежитие ПВГУС",
        "29.05.2022 - 29.05.2024",
        1,
        "От 0 ₽ "
    ),
    LifeProfileItem(
        "4",
        "Студенческое общежитие ПВГУС",
        "29.05.2022 - 29.05.2024",
        1,
        "От 0 ₽ "
    ),
    LifeProfileItem(
        "5",
        "Спортивно-оздоровительный лагерь <Горизонт>",
        "25.06.2022 - 25.06.2024",
        1,
        "От 0 ₽ "
    )
)

