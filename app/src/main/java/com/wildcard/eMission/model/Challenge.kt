package com.wildcard.eMission.model

//added place for finnish translations

data class Challenge (
    val cId: String,
    val name: String,
    val description: String,
    val description_fin: String,
    val points: Int,
    val challengePack: ChallengePack,
    val singleTask: Boolean,
    var status: CompleteStatus,
    val info: Info?,
    val diet: ArrayList<Diet>,
    val housingType: ArrayList<HousingType>,
    val transportation: ArrayList<Transportation>
)

enum class CompleteStatus {
    COMPLETE,
    ONGOING,
    UNSTARTED
}

enum class ChallengePack {
    BASIC,
    SET_1,
    SET_2,
    SET_3,
    SET_4
}