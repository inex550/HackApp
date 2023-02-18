package ru.faaen.hackapp.features.wherego.data.models

import ru.faaen.hackapp.core.ui.recycler.BaseItem

enum class Filters(
    val filter: String,
    val title: String,
    val options: List<FilterOption>,
): BaseItem {
    FEDERAL_DISTRICT("federal_district", "Федеральный округ", listOf(
        FilterOption("Дальневосточный"),
        FilterOption("Приволжский"),
        FilterOption("Северо-Западный"),
        FilterOption("Северо-Кавказский"),
        FilterOption("Сибирский"),
        FilterOption("Уральский"),
        FilterOption("Центральный"),
        FilterOption("Южный"),
    )),
    SUBJECT_RF("subject_rf", "Субъект РФ", listOf(
        FilterOption("Алтайский край"),
        FilterOption("Амурская область"),
        FilterOption("Архангельская область"),
        FilterOption("Астраханская область"),
        FilterOption("Белгородская область"),
        FilterOption("Белгородская область,город Белгород"),
        FilterOption("Владимирская область"),
        FilterOption("Волгоградская область"),
        FilterOption("Вологодская область"),
        FilterOption("Еврейская автономная область"),
        FilterOption("Забайкальский край"),
        FilterOption("Ивановская область"),
        FilterOption("Иркутская область"),
        FilterOption("Калининградская область"),
        FilterOption("Калужская область"),
        FilterOption("Карачаево-Черкесская Республика"),
        FilterOption("Кемеровская область"),
        FilterOption("Кировская область"),
        FilterOption("Костромская область"),
        FilterOption("Красноярский край"),
        FilterOption("Курганская область"),
        FilterOption("Курская область"),
        FilterOption("Москва"),
        FilterOption("Московская область"),
        FilterOption("Мурманская область"),
        FilterOption("Нижегородская область"),
        FilterOption("Новгородская область"),
        FilterOption("Новосибирская область"),
        FilterOption("Омская область"),
        FilterOption("Оренбургская область"),
    )),
    LOCALLY("locally", "Населенный пункт", listOf(
        FilterOption("Артыбаш"),
        FilterOption("Архангельск"),
        FilterOption("Астрахань"),
        FilterOption("Байкал оз., с. Максимиха, респ Бурятия"),
        FilterOption("Байкал, оз. (Иркутск)"),
        FilterOption("Балашиха"),
        FilterOption("Барнаул"),
        FilterOption("Белгород"),
        FilterOption("Владивосток"),
        FilterOption("Калуга"),
        FilterOption("Красноярск"),
        FilterOption("Новосибирск"),
        FilterOption("Псков"),
        FilterOption("Ростов-на-Дону"),
        FilterOption("Саранск"),
        FilterOption("Ставрополь"),
        FilterOption("Улан-Удэ"),
        FilterOption("Ханты-Мансийск"),
        FilterOption("Чита"),
    )),
    EDUCATIONAL_ORGANIZATION("educational_organization", "Образовательная ораганизация", listOf(
        FilterOption("АГГПУ им. В.М.Шукшина"),
        FilterOption("АлтГУ","БФУ им. И. Канта"),
        FilterOption("БашГУ"),
        FilterOption("ГГПИ им. В.Г. Короленко"),
        FilterOption("ЗабГУ"),
        FilterOption("ДГУ"),
        FilterOption("КГУ"),
        FilterOption("КФУ"),
        FilterOption("МАРХИ"),
    )),
    PLACING_TYPE("placing_type", "Тип размещения", listOf(
        FilterOption("1-но местный номер", "1"),
        FilterOption("2-х местный номер", "2"),
        FilterOption("3-х местный номер", "3"),
        FilterOption("4-х местный номер", "4"),
        FilterOption("5-ти местный номер", "5"),
    )),
    NUTRITION("nutrition", "Питание", listOf(
        FilterOption("Без питания"),
        FilterOption("Завтрак и ужин"),
        FilterOption("Полный пансион"),
        FilterOption("Только завтрак"),
    )),
    SORT_BY_RATING("sort_by_rating", "Сортировка по рейтингу", listOf(
        FilterOption("По возрастанию"),
        FilterOption("По убыванию"),
    ));
}
