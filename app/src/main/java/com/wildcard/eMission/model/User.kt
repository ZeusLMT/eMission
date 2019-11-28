package com.wildcard.eMission.model

data class User (
    var uId: String,
    var name: String,
    var age: Int?,
    var carbonFootprint: Float,
    var rewardPoints: Int,
    val rewards: ArrayList<Reward>,
    val completed_challenges: ArrayList<Challenge>,
    val lifestyle: Lifestyle
)