package com.wildcard.eMission.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wildcard.eMission.model.Challenge
import com.wildcard.eMission.model.CompleteStatus
import com.wildcard.eMission.model.Diet
import com.wildcard.eMission.model.Transportation

class HomeViewModel : ViewModel() {
    val carbonSaved = MutableLiveData<Int>().apply { value = 2259 }

    var todayChallenges = MutableLiveData<ArrayList<Challenge>>()
    var allChallenges = getAllChallengesFromDatabase()

    private fun getAllChallengesFromDatabase(): ArrayList<Challenge> {
        val challenges = ArrayList<Challenge>()
        challenges.add(
            Challenge(
                cId = "1",
                name = "Tofu vs. Meat",
                description = "Eat tofu instead of meat",
                description_fin = "Syö tofua lihan sijasta",
                points = 915,
                challengePack = "basic",
                singleTask = true,
                status = CompleteStatus.UNSTARTED,
                info = null,
                diet = arrayListOf(Diet.NON_VEGAN),
                transportation = arrayListOf(),
                housingType = arrayListOf()
            )
        )

        challenges.add(
            Challenge(
                cId = "2",
                name = "Take a bus",
                description = "Take bus to work and home",
                description_fin = "Tee työmatkasi bussilla",
                points = 1839,
                challengePack = "basic",
                singleTask = false,
                status = CompleteStatus.UNSTARTED,
                info = null,
                diet = arrayListOf(),
                transportation = arrayListOf(Transportation.CAR),
                housingType = arrayListOf()
            )
        )

        challenges.add(
            Challenge(
                cId = "3",
                name = "No TV for today",
                description = "Don't watch TV for an evening",
                description_fin = "Älä katso televisiota lainkaan yhtenä iltana",
                points = 205,
                singleTask = true,
                challengePack = "basic",
                status = CompleteStatus.UNSTARTED,
                info = null,
                diet = arrayListOf(),
                transportation = arrayListOf(),
                housingType = arrayListOf()
            )
        )

        challenges.add(
            Challenge(
                cId = "4",
                name = "I rather walk…",
                description = "Walk 4000 steps",
                description_fin = "Kävele 4000 askelta",
                points = 2259,
                challengePack = "basic",
                singleTask = true,
                status = CompleteStatus.COMPLETE,
                info = null,
                diet = arrayListOf(),
                transportation = arrayListOf(
                    Transportation.CAR,
                    Transportation.BICYCLE,
                    Transportation.BUS,
                    Transportation.TRAIN
                ),
                housingType = arrayListOf()
            )
        )

        challenges.add(
            Challenge(
                cId = "5",
                name = "Milk vs. Cream",
                description = "Use milk instead of cream twice",
                description_fin = "Käytä maitoa kerman sijaan kahdesti",
                points = 152,
                challengePack = "basic",
                singleTask = false,
                status = CompleteStatus.UNSTARTED,
                info = null,
                diet = arrayListOf(Diet.NON_VEGAN),
                transportation = arrayListOf(),
                housingType = arrayListOf()
            )
        )

        challenges.add(
            Challenge(
                cId = "6",
                name = "Lights out",
                description = "Turn off unnecessary lights in 2 rooms",
                description_fin = "Sammuta kahdesta huoneesta turhat valot",
                points = 50,
                challengePack = "basic",
                singleTask = false,
                status = CompleteStatus.UNSTARTED,
                info = null,
                diet = arrayListOf(),
                transportation = arrayListOf(),
                housingType = arrayListOf()
            )
        )
        challenges.add(
            Challenge(
                cId = "7",
                name = "Happy Eggs",
                description = "Avoid eating eggs for today",
                description_fin = "Älä syö kananmunia tänään",
                points = 50,
                challengePack = "basic",
                singleTask = true,
                status = CompleteStatus.UNSTARTED,
                info = null,
                diet = arrayListOf(Diet.NON_VEGAN),
                transportation = arrayListOf(),
                housingType = arrayListOf()
            )
        )

        return challenges
    }
}