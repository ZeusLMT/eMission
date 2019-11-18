package com.wildcard.eMission.model

data class User (
    val uId: String,
    val name: String,
    val age: Int?,
    val carbonFootprint: Float,
    val rewardPoints: Int,
    val rewards: ArrayList<Reward>,
    val completed_challenges: ArrayList<Challenge>,
    val lifestyle: Lifestyle
)