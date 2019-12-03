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
                description_fin = "Syö kasvispainotteisesti lihan sijaan",
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
                description_fin = "Syö vegaanisesti lihan sijasta",
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
                description_fin = "Syö kasviperäisiä rasvoja (margariini, öljy) voin sijasta",
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
                description_fin = "Syö tofua lihan sijasta",
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
                description_fin = "Syö siipikarjanlihaa naudanlihan sijasta",
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
                description_fin = "Syö kahdella aterialla perunoita riisin sijasta",
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
                description_fin = "Käytä palkokasveja riisin sijasta",
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
                description_fin = "Käytä pastaa riisin sijasta",
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
                description_fin = "Käytä maitoa kerman sijasta kahdesti",
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
                description_fin = "Syö kalaa lihan sijasta",
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
                description_fin = "Käytä kasviksia riisin tilalla",
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
                description_fin = "Vältä kananmunien syömistä tämän päivän ajan",
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
                description_fin = "Vältä juuston syömistä tämän päivän ajan",
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
                description_fin = "Kokeile siipikarjanlihaa sianlihan sijasta",
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
                description_fin = "Tee työmatkasi kahdesti bussilla autoilun sijaan",
                points = 1839,
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
                description_fin = "Tee työmatkasi junalla autolla matkustamisen sijaan",
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
                description_fin = "Käytä joukkoliikennettä tänään",
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
                description_fin = "Kävele kouluun/töihin",
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
                description_fin = "Mene kouluun/töihin pyöräillen",
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
                description_fin = "Kävele 20 km",
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
                description_fin = "Pyöräile 20 km",
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
                description_fin = "Älä katso lainkaan televisiota illalla",
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
                description_fin = "Älä käytä PC:tä lainkaan yhden päivän ajan",
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
                description_fin = "Sammuta kahdesta huoneesta turhat valot",
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
                description_fin = "Älä osta kahvia kertakäyttökupissa",
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
                description_fin = "Älä osta pullovettä",
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
                description_fin = "Käytä uudellenkäytettävää vesipulloa",
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