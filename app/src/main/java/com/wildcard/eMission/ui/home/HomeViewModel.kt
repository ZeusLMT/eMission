package com.wildcard.eMission.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wildcard.eMission.model.*

class HomeViewModel : ViewModel() {
    val carbonSaved = MutableLiveData<Int>()

    var todayChallenges = MutableLiveData<ArrayList<Challenge>>()
    var allChallenges = getAllChallengesFromDatabase()

    private fun getAllChallengesFromDatabase(): ArrayList<Challenge> {
        val challenges = ArrayList<Challenge>()

        challenges.add(
            Challenge(
                cId = "001",
                name = "Happy Animal I",
                description = "Eat vegetarian instead of meat",
                points = 1436,
                challengePack = ChallengePack.BASIC,
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
                cId = "002",
                name = "Happy Animal II",
                description = "Eat vegan instead of meat",
                points = 1743,
                challengePack = ChallengePack.BASIC,
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
                cId = "003",
                name = "Plant-Based Fats vs. Butter",
                description = "Use a plant-based fats (margarine, oil) instead of butter",
                points = 1229,
                challengePack = ChallengePack.SET_1,
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
                cId = "004",
                name = "Tofu vs. Meat",
                description = "Eat tofu instead of meat",
                points = 1831,
                challengePack = ChallengePack.BASIC,
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
                cId = "005",
                name = "Less Red Meat",
                description = "Eat poultry meat instead of beef meat",
                points = 3286,
                challengePack = ChallengePack.BASIC,
                singleTask = true,
                status = CompleteStatus.UNSTARTED,
                info = null,
                diet = arrayListOf(),
                transportation = arrayListOf(),
                housingType = arrayListOf()
            )
        )

        challenges.add(
            Challenge(
                cId = "006",
                name = "Potato vs. Rice",
                description = "Use Potatoes instead of Rice in 2 meals",
                points = 837,
                challengePack = ChallengePack.BASIC,
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
                cId = "007",
                name = "Legume vs. Rice",
                description = "Use legumes instead of Rice",
                points = 518,
                challengePack = ChallengePack.SET_2,
                singleTask = true,
                status = CompleteStatus.UNSTARTED,
                info = null,
                diet = arrayListOf(),
                transportation = arrayListOf(),
                housingType = arrayListOf()
            )
        )

        challenges.add(
            Challenge(
                cId = "008",
                name = "Pasta vs. Rice",
                description = "Use Pasta instead of Rice",
                points = 794,
                challengePack = ChallengePack.SET_3,
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
                cId = "009",
                name = "Milk vs. Cream",
                description = "Use milk instead of cream twice",
                points = 760,
                challengePack = ChallengePack.BASIC,
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
                cId = "010",
                name = "Fish vs. Meat",
                description = "Eat fish instead of meat",
                points = 1367,
                challengePack = ChallengePack.BASIC,
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
                cId = "011",
                name = "Vegetables vs. Rice",
                description = "Use vegetables instead of Rice",
                points = 795,
                challengePack = ChallengePack.SET_3,
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
                cId = "012",
                name = "Happy Eggs",
                description = "Avoid eating eggs for today",
                points = 150,
                challengePack = ChallengePack.BASIC,
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
                cId = "013",
                name = "Live without Cheese",
                description = "Avoid eating cheese for today",
                points = 784,
                challengePack = ChallengePack.BASIC,
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
                cId = "014",
                name = "Happy Pork",
                description = "Try poultry instead of pork",
                points = 754,
                challengePack = ChallengePack.BASIC,
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
                cId = "015",
                name = "Take a bus",
                description = "Commute with bus instead of private car twice",
                points = 613,
                challengePack = ChallengePack.BASIC,
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
                cId = "016",
                name = "Station to Station",
                description = "Commute with train instead of private car",
                points = 1294,
                challengePack = ChallengePack.BASIC,
                singleTask = true,
                status = CompleteStatus.UNSTARTED,
                info = null,
                diet = arrayListOf(),
                transportation = arrayListOf(Transportation.CAR),
                housingType = arrayListOf()
            )
        )

        challenges.add(
            Challenge(
                cId = "017",
                name = "Public transportation",
                description = "Use public transportation for today",
                points = 1120,
                challengePack = ChallengePack.BASIC,
                singleTask = true,
                status = CompleteStatus.UNSTARTED,
                info = null,
                diet = arrayListOf(),
                transportation = arrayListOf(Transportation.CAR),
                housingType = arrayListOf()
            )
        )

        challenges.add(
            Challenge(
                cId = "018",
                name = "I rather walk…",
                description = "Walk to school/work",
                points = 1506,
                challengePack = ChallengePack.BASIC,
                singleTask = true,
                status = CompleteStatus.UNSTARTED,
                info = null,
                diet = arrayListOf(),
                transportation = arrayListOf(
                    Transportation.CAR,
                    Transportation.BUS,
                    Transportation.TRAIN
                ),
                housingType = arrayListOf()
            )
        )

        challenges.add(
            Challenge(
                cId = "019",
                name = "I <3 My Bicycle",
                description = "Cycle to school/work",
                points = 1506,
                challengePack = ChallengePack.BASIC,
                singleTask = true,
                status = CompleteStatus.UNSTARTED,
                info = null,
                diet = arrayListOf(),
                transportation = arrayListOf(
                    Transportation.CAR,
                    Transportation.BUS,
                    Transportation.TRAIN
                ),
                housingType = arrayListOf()
            )
        )

        challenges.add(
            Challenge(
                cId = "020",
                name = "Marathon training",
                description = "Walk 20km",
                points = 3012,
                challengePack = ChallengePack.SET_2,
                singleTask = true,
                status = CompleteStatus.UNSTARTED,
                info = null,
                diet = arrayListOf(),
                transportation = arrayListOf(
                    Transportation.CAR,
                    Transportation.BUS,
                    Transportation.TRAIN
                ),
                housingType = arrayListOf()
            )
        )

        challenges.add(
            Challenge(
                cId = "021",
                name = "Tour de france",
                description = "Cycle for 20km",
                points = 3012,
                challengePack = ChallengePack.SET_2,
                singleTask = true,
                status = CompleteStatus.UNSTARTED,
                info = null,
                diet = arrayListOf(),
                transportation = arrayListOf(
                    Transportation.CAR,
                    Transportation.BUS,
                    Transportation.TRAIN
                ),
                housingType = arrayListOf()
            )
        )

        challenges.add(
            Challenge(
                cId = "022",
                name = "No TV for today",
                description = "Don't watch TV for an evening",
                points = 205,
                singleTask = true,
                challengePack = ChallengePack.SET_2,
                status = CompleteStatus.UNSTARTED,
                info = null,
                diet = arrayListOf(),
                transportation = arrayListOf(),
                housingType = arrayListOf()
            )
        )

        challenges.add(
            Challenge(
                cId = "023",
                name = "Unplugged",
                description = "Don't use PC for a day",
                points = 300,
                singleTask = true,
                challengePack = ChallengePack.SET_3,
                status = CompleteStatus.UNSTARTED,
                info = null,
                diet = arrayListOf(),
                transportation = arrayListOf(),
                housingType = arrayListOf()
            )
        )

        challenges.add(
            Challenge(
                cId = "024",
                name = "Apagando las luces",
                description = "Turn off unnecessary lights in 2 rooms",
                points = 50,
                challengePack = ChallengePack.BASIC,
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
                cId = "025",
                name = "BYOC",
                description = "Don't buy take out coffee with disposable cup",
                points = 25,
                challengePack = ChallengePack.SET_1,
                singleTask = true,
                status = CompleteStatus.UNSTARTED,
                info = null,
                diet = arrayListOf(),
                transportation = arrayListOf(),
                housingType = arrayListOf()
            )
        )

        challenges.add(
            Challenge(
                cId = "026",
                name = "Tap water <3",
                description = "Don't buy water in plastic bottle",
                points = 83,
                challengePack = ChallengePack.SET_3,
                singleTask = true,
                status = CompleteStatus.UNSTARTED,
                info = null,
                diet = arrayListOf(),
                transportation = arrayListOf(),
                housingType = arrayListOf()
            )
        )

        challenges.add(
            Challenge(
                cId = "027",
                name = "Reduce and Reuse",
                description = "Use a reusable water bottle",
                points = 83,
                challengePack = ChallengePack.SET_4,
                singleTask = true,
                status = CompleteStatus.UNSTARTED,
                info = null,
                diet = arrayListOf(),
                transportation = arrayListOf(),
                housingType = arrayListOf()
            )
        )

        return challenges
    }
}