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


) {
    override fun toString(): String {
        return "User(name='$name', age=$age, carbonFootprint=$carbonFootprint, carbonSaved=$carbonSaved, rewardPoints=$rewardPoints, rewards=$rewards, completed_challenges=$completed_challenges, diet=$diet, housingType=$housingType, transportation=$transportation)"
    }
}
