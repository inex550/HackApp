package ru.faaen.hackapp.features.map


data class Campus(
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val description: String
)


val cities = listOf(
    Campus("Moscow", 55.755826, 37.6172999, "Moscow is the capital and largest city of Russia, with a population of over 12 million people. It is known for its iconic landmarks such as Red Square, Saint Basil's Cathedral, and the Kremlin"),
    Campus("Saint Petersburg", 59.9342802, 30.3350986, "St. Petersburg is a city in Russia located on the Neva River. It is known for its beautiful architecture, historic landmarks, and rich cultural heritage."),
    Campus("Kazan", 55.790278, 49.1228027, "Kazan, the capital of the Republic of Tatarstan, is a city with a long history located about 820 km east of Moscow on the left bank of the Volga River. This is one of the largest economic, scientific, educational, religious, cultural, and sports centers of Russia."),
    Campus("Novosibirsk", 55.0083525, 82.9357327, "Novosibirsk is a city in Siberia, Russia, and is the third-most populous city in the country. It is known for its science and technology institutes, as well as its vibrant arts and culture scene."),
    Campus("Yekaterinburg", 56.8380127, 60.6057025, "Yekaterinburg"),
    Campus("Nizhny Novgorod", 56.3286763, 44.0020576, "Nizhny Novgorod"),
    Campus("Rostov-on-Don", 47.2363829, 39.7144244, "Rostov-on-Don"),
    Campus("Chelyabinsk", 55.154278, 61.4343, "Chelyabinsk"),
    Campus("Omsk", 54.991667, 73.366667, "Omsk"),
    Campus("Ufa", 54.749335, 55.972633, "Ufa"),
    Campus("Krasnoyarsk", 56.012079, 92.877281, "Krasnoyarsk"),
    Campus("Voronezh", 51.660281, 39.200269, "Voronezh"),
    Campus("Samara", 53.201975, 45.968855, "Samara"),
    Campus("Krasnodar", 45.034188, 38.894921, "Krasnodar"),
    Campus("Barnaul", 53.3552, 83.7612, "Barnaul"),
    Campus("Khabarovsk", 48.482780, 135.091608, "Khabarovsk"),
    Campus("Irkutsk", 52.286974, 104.305018, "Irkutsk"),

    Campus("Общежитие ГГНТУ №2", 43.31774309765962, 45.72499042897583, "Заявка на размещение студентов с указанием данных проживающего и сроков проживания. \\nСканированный копии, следующих документов:\\nПаспорт\\nСнилс\\nМедицинский полис\\nСправка 086\\nСправка из деканата с указанием даты завершения обучения.")
)

