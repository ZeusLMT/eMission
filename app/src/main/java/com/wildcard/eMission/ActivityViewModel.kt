package com.wildcard.eMission

import androidx.lifecycle.ViewModel
import com.wildcard.eMission.model.Diet
import com.wildcard.eMission.model.HousingType
import com.wildcard.eMission.model.Transportation
import com.wildcard.eMission.model.User

class ActivityViewModel : ViewModel() {
    val user = User(
        name = "Test User",
        age = null,
        carbonFootprint = 0,
        carbonSaved = 2000,
        rewardPoints = 500,
        rewards = arrayListOf(),
        completed_challenges = arrayListOf(),
        diet = Diet.NON_VEGAN,
        housingType = HousingType.APARTMENT,
        transportation = arrayListOf(Transportation.BUS, Transportation.TRAIN)
    )

    fun updateUserData(update: ((User) -> Unit)) {
        update(user)
        //Write to SharePrefs here
    }
}