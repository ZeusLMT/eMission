package com.wildcard.eMission.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wildcard.eMission.model.*

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Home Fragment"
    }
    val text: LiveData<String> = _text

    var todayChallenges = MutableLiveData<ArrayList<Challenge>>()

    fun generateTodayChallenges() {
        val challenges = ArrayList<Challenge>()
        challenges.add(
            Challenge(
                cId = "1",
                name = "Tofu vs. Meat",
                description = "Eat tofu instead of meat",
                description_fin = "Syö tofua lihan sijasta",
                points = 150,
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
                description_fin = "Käytä bussia töihin mennessä",
                points = 300,
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
                description_fin = "Älä katso televisiota koko iltaa",
                points = 180,
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
                description_fin = "Kävele 4000 askelta",
                points = 450,
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