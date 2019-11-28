package com.wildcard.eMission

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.FirebaseDatabase
import com.wildcard.eMission.model.Lifestyle
import com.wildcard.eMission.model.User

class ActivityViewModel : ViewModel() {
    var firebaseDatabase: FirebaseDatabase? = null

    val user = MutableLiveData<User>(
        User(
            uId = "",
            name = "",
            age = null,
            carbonFootprint = 0f,
            rewardPoints = 0,
            rewards = arrayListOf(),
            completed_challenges = arrayListOf(),
            lifestyle = Lifestyle(
                ageGroup = arrayListOf(),
                diet = arrayListOf(),
                transportation = arrayListOf(),
                housingType = arrayListOf(),
                powerConsumption = arrayListOf()
            )
        )
    )
}