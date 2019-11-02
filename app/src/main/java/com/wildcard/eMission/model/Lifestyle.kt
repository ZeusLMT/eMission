package com.wildcard.eMission.model

data class Lifestyle (
    val ageGroup: ArrayList<AgeGroup>,
    val diet: ArrayList<Diet>,
    val transportation: ArrayList<Transportation>,
    val housingType: ArrayList<HousingType>,
    val powerConsumption: ArrayList<Int>
)

enum class AgeGroup {
    CHILDREN,
    TEENAGER,
    ADULT
}
enum class Diet {
    VEGAN,
    NON_VEGAN
}

enum class Transportation {
    CAR,
    BICYCLE,
    BUS,
    TRAIN,
    WALKING
}

enum class HousingType {
    APARTMENT,
    TOWN_HOUSE,
    DETACHED_HOUSE
}