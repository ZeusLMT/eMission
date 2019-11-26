package com.wildcard.eMission.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wildcard.eMission.model.*

class HomeViewModel : ViewModel() {
    val carbonSaved = MutableLiveData<Int>().apply { value = 2259 }

    var todayChallenges = MutableLiveData<ArrayList<Challenge>>()

    fun generateTodayChallenges() {
        val challenges = ArrayList<Challenge>()
        challenges.add(
            Challenge(
                cId = "1",
                name = "Tofu vs. Meat",
                description = "Eat tofu instead of meat",
                points = 915,
                status = CompleteStatus.UNSTARTED,
                info = null,
                matchingLifestyle = Lifestyle(
                    ageGroup = arrayListOf(AgeGroup.ADULT, AgeGroup.TEENAGER),
                    diet = arrayListOf(Diet.NON_VEGAN),
                    transportation = arrayListOf(),
                    housingType = arrayListOf(),
                    powerConsumption = arrayListOf()
                )
            )
        )

        challenges.add(
            Challenge(
                cId = "2",
                name = "Take a bus",
                description = "Take bus to work",
                points = 1839,
                status = CompleteStatus.UNSTARTED,
                info = null,
                matchingLifestyle = Lifestyle(
                    ageGroup = arrayListOf(AgeGroup.ADULT),
                    diet = arrayListOf(),
                    transportation = arrayListOf(Transportation.CAR),
                    housingType = arrayListOf(),
                    powerConsumption = arrayListOf()
                )
            )
        )

        challenges.add(
            Challenge(
                cId = "3",
                name = "No TV for today",
                description = "Don't watch TV for an evening",
                points = 205,
                status = CompleteStatus.UNSTARTED,
                info = null,
                matchingLifestyle = Lifestyle(
                    ageGroup = arrayListOf(AgeGroup.ADULT, AgeGroup.TEENAGER),
                    diet = arrayListOf(),
                    transportation = arrayListOf(),
                    housingType = arrayListOf(),
                    powerConsumption = arrayListOf(PowerConsumption.HIGH, PowerConsumption.VERY_HIGH)
                )
            )
        )

        challenges.add(
            Challenge(
                cId = "4",
                name = "I rather walk…",
                description = "Walk 4000 steps",
                points = 2259,
                status = CompleteStatus.COMPLETE,
                info = null,
                matchingLifestyle = Lifestyle(
                    ageGroup = arrayListOf(AgeGroup.ADULT, AgeGroup.TEENAGER, AgeGroup.CHILDREN),
                    diet = arrayListOf(),
                    transportation = arrayListOf(Transportation.CAR, Transportation.BICYCLE, Transportation.BUS, Transportation.TRAIN),
                    housingType = arrayListOf(),
                    powerConsumption = arrayListOf()
                )
            )
        )

        todayChallenges.value = challenges
    }
}