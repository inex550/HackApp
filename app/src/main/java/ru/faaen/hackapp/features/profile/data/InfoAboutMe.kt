package ru.faaen.hackapp.features.profile.data


data class InfoAboutMe(
    var name: String,
    var lastName: String,
    var fatherName: String,
    var mail: String,
    var password: String,
    var role: String,
    var pol: String,
    var data: String,
    var git: String
)


var InfoAboutMes = InfoAboutMe("Антон","Никитов", "Ахмедович", "iam@gmail.com", "123456", "Студент", "Мужской", "01-01-2000", "https://github.com/user1")

