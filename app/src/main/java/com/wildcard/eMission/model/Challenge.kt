package com.wildcard.eMission.model

data class Challenge (
    val cId: String,
    val name: String,
    val description: String,
    val points: Int,
    val status: CompleteStatus,
    val info: Info,
    val matchingLifestyle: Lifestyle
)

enum class CompleteStatus {
    COMPLETE,
    ONGOING,
    UNSTARTED
}