package com.wildcard.eMission

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wildcard.eMission.model.*

class ActivityViewModel : ViewModel() {
    var user = User(
        title = "Test User",
        picture = "",
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
    var userDataUpdated = MutableLiveData<Boolean>(false)
    var writeUserToSP = MutableLiveData<Boolean>(false)
    var unlockedChallengePacks = ArrayList<ChallengePack>(arrayListOf(ChallengePack.BASIC))

    fun updateUserData(update: ((User) -> Unit)) {
        update(user)

        userDataUpdated.value = !userDataUpdated.value!!
        //Write to SharePrefs here
        writeUserToSP.value = true
    }
}