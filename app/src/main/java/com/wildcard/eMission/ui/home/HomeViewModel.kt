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
                name_fin = "Onnellinen eläin I",
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
                name_fin = "Onnellinen eläin II",
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
                name_fin = "Kasviperäiset rasvat vs voi",
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
                name_fin = "Tofu vs liha",
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
                name_fin = "Vähemmän punaista lihaa",
                description = "Eat poultry meat instead of beef meat",
                description_fin = "Syö siipikarjanlihaa naudanlihan sijasta",
                points = 3286,
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
                cId = "006",
                name = "Potato vs. Rice",
                name_fin = "Peruna vs riisi",
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
                name_fin = "Palkokasvit vs riisi",
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
                name_fin = "Pasta vs riisi",
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
                name_fin = "Maito vs kerma",
                description = "Use milk instead of cream twice",
                description_fin = "Käytä maitoa kerman sijasta kahdesti",
                points = 760,
                challengePack = ChallengePack.BASIC,
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
                cId = "010",
                name = "Fish vs. Meat",
                name_fin = "Kala vs liha",
                description = "Eat fish instead of meat",
                description_fin = "Syö kalaa lihan sijasta",
                points = 1367,
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
                cId = "011",
                name = "Vegetables vs. Rice",
                name_fin = "Vihannekset vs riisi",
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
                name_fin = "Onneliset munat",
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
                name_fin = "Juustoton elämä",
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
                name_fin = "Onnellinen possu",
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
                name_fin = "Nappaa bussi",
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
                name_fin = "Asemalta toiselle",
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
                name_fin = "Joukkoliikenne",
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
                name_fin = "Kävelen mieluummin",
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
                name_fin= "Rakastan polkupyörääni",
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
                name_fin = "Maratoonille treenausta",
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
                name_fin = "Tour de france",
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
                name_fin = "Ei TV:lle tänään",
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
                name_fin = "Unplugged",
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
                name_fin = "Apagnando las luces",
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
                name_fin = "BYOC",
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
                name_fin = "Hanavesi <3",
                description = "Don't buy water in plastic bottle",
                description_fin = "Älä osta vettä muovipullossa",
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
                name_fin = "Vähennä ja uudellenkäytä",
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