package com.wildcard.eMission.model

data class User (
    var name: String,
    var age: Int?,
    var carbonFootprint: Long,
    var carbonSaved: Long,
    var rewardPoints: Int,
    var rewards: ArrayList<Reward>,
    var completed_challenges: ArrayList<Challenge>,
    var diet: Diet,
    var housingType: HousingType,
    var transportation: ArrayList<Transportation>
)