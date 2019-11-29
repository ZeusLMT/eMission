package com.wildcard.eMission.model

data class Challenge (
    val cId: String,
    val name: String,
    val description: String,
    val points: Int,
    val challengePack: String,
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