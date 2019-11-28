package com.wildcard.eMission.model

//added place for finnish translations

data class Challenge (
    val cId: String,
    val name: String,
    val description: String,
    val description_fin: String,
    val points: Int,
    var status: CompleteStatus,
    val info: Info?,
    val matchingLifestyle: Lifestyle
)

enum class CompleteStatus {
    COMPLETE,
    ONGOING,
    UNSTARTED
}