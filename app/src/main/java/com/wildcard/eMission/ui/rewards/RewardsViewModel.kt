package com.wildcard.eMission.ui.rewards

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wildcard.eMission.model.*

class RewardsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Rewards Fragment"
    }
    val text: LiveData<String> = _text

    val userPoints = MutableLiveData<Int>()

    val rewardsList = MutableLiveData<ArrayList<Reward>>()


    fun getRewardsList(): ArrayList<Reward> {
        val rewards = ArrayList<Reward>()

        rewards.add(
            Reward(
                rId = "01",
                name = "Title: Private",
                name_fin = "Otsikko: Yksityinen",
                image = "file:///android_asset/rewards_title_green.jpg",
                tier = RewardTier.PRIVATE_GREEN,
                type = RewardType.TITLE,
                points = 500,
                status = RewardStatus.AVAILABLE,
                content = "Private Green"
            )
        )

        rewards.add(
            Reward(
                rId = "02",
                name = "Avatar: Green",
                name_fin = "Avatar: Vihreä",
                image = "file:///android_asset/rewards_profile_1.jpg",
                tier = RewardTier.PRIVATE_GREEN,
                type = RewardType.PROFILE_PIC,
                points = 500,
                status = RewardStatus.AVAILABLE,
                content = "file:///android_asset/rewards_profile_1.jpg"
            )
        )

        rewards.add(
            Reward(
                rId = "03",
                name = "Title: Corporal",
                name_fin = "Otsikko: Korporaali",
                image = "file:///android_asset/rewards_title_bee.jpg",
                tier = RewardTier.CORPORAL_BEE,
                type = RewardType.TITLE,
                points = 500,
                status = RewardStatus.AVAILABLE,
                content = "Corporal Bee"
            )
        )

        rewards.add(
            Reward(
                rId = "04",
                name = "Pack: Bee",
                name_fin = "Paketti: Mehiläinen",
                image = "",
                tier = RewardTier.CORPORAL_BEE,
                type = RewardType.CHALLENGE_PACK,
                points = 500,
                status = RewardStatus.AVAILABLE,
                content = ChallengePack.SET_1
            )
        )

        rewards.add(
            Reward(
                rId = "05",
                name = "Title: Sergeant",
                name_fin = "Otsikko: Kersantti",
                image = "file:///android_asset/rewards_title_flower.jpg",
                tier = RewardTier.SERGEANT_FLOWER,
                type = RewardType.TITLE,
                points = 1000,
                status = RewardStatus.AVAILABLE,
                content = "Sergeant Flower"
            )
        )

        rewards.add(
            Reward(
                rId = "06",
                name = "Avatar: Flower",
                name_fin ="Avatar: Kukka",
                image = "file:///android_asset/rewards_profile_2.jpg",
                tier = RewardTier.SERGEANT_FLOWER,
                points = 1000,
                type = RewardType.PROFILE_PIC,
                status = RewardStatus.AVAILABLE,
                content = "file:///android_asset/rewards_profile_2.jpg"
            )
        )

        rewards.add(
            Reward(
                rId = "07",
                name = "Title: Lieutenant",
                name_fin = "Otsikko: Luutnantti",
                image = "file:///android_asset/rewards_title_tree.jpg",
                tier = RewardTier.LIEUTENANT_TREE,
                points = 1000,
                type = RewardType.TITLE,
                status = RewardStatus.AVAILABLE,
                content = "Lieutenant Tree"
            )
        )

        rewards.add(
            Reward(
                rId = "08",
                name = "Pack: Flower",
                name_fin = "Pakkaus: Kukka",
                image = "",
                tier = RewardTier.LIEUTENANT_TREE,
                points = 1000,
                type = RewardType.CHALLENGE_PACK,
                status = RewardStatus.AVAILABLE,
                content = ChallengePack.SET_2
            )
        )

        rewards.add(
            Reward(
                rId = "09",
                name = "Title: Captain",
                name_fin = "Otsikko: Kapteeni",
                image = "file:///android_asset/rewards_title_wood.jpg",
                tier = RewardTier.CAPTAIN_WOOD,
                points = 1500,
                type = RewardType.TITLE,
                status = RewardStatus.AVAILABLE,
                content = "Captain Wood"
            )
        )

        rewards.add(
            Reward(
                rId = "10",
                name = "Avatar: Wood",
                name_fin = "Avatar: Metsä",
                image = "file:///android_asset/rewards_profile_3.jpg",
                tier = RewardTier.CAPTAIN_WOOD,
                points = 2000,
                type = RewardType.PROFILE_PIC,
                status = RewardStatus.AVAILABLE,
                content = "file:///android_asset/rewards_profile_3.jpg"
            )
        )

        rewards.add(
            Reward(
                rId = "11",
                name = "Dark Theme",
                name_fin = "Tumma Teema",
                tier = RewardTier.CAPTAIN_WOOD,
                image = "",
                points = 5000,
                type = RewardType.THEME,
                status = RewardStatus.AVAILABLE,
                content = "DARK"
            )
        )

        rewards.add(
            Reward(
                rId = "12",
                name = "Title: Major",
                name_fin = "Otsikko: Majuri",
                image = "file:///android_asset/rewards_title_nature.jpg",
                tier = RewardTier.MAJOR_NATURE,
                points = 4000,
                type = RewardType.TITLE,
                status = RewardStatus.AVAILABLE,
                content = "Major Nature"
            )
        )

        rewards.add(
            Reward(
                rId = "13",
                name = "Pack: Nature",
                name_fin = "Paketti: Luonto",
                image = "",
                tier = RewardTier.MAJOR_NATURE,
                points = 4000,
                type = RewardType.CHALLENGE_PACK,
                status = RewardStatus.AVAILABLE,
                content = ChallengePack.SET_3
            )
        )


        rewards.add(
            Reward(
                rId = "14",
                name = "Title: Colonel",
                name_fin = "Otsikko: Eversti",
                image = "file:///android_asset/rewards_title_environment.jpg",
                tier = RewardTier.COLONEL_ENVIRONMENT,
                points = 6000,
                type = RewardType.TITLE,
                status = RewardStatus.AVAILABLE,
                content = "Colonel Environment"
            )
        )

        rewards.add(
            Reward(
                rId = "15",
                name = "Title: General",
                name_fin = "Otsikko: Kenraali",
                image = "file:///android_asset/rewards_title_climate.jpg",
                tier = RewardTier.GENERAL_CLIMATE,
                points = 8000,
                type = RewardType.TITLE,
                status = RewardStatus.AVAILABLE,
                content = "General Climate"
            )
        )

        rewards.add(
            Reward(
                rId = "16",
                name = "Pack: Climate",
                name_fin = "Pakkaus: Ilmasto",
                image = "",
                tier = RewardTier.GENERAL_CLIMATE,
                points = 8000,
                type = RewardType.CHALLENGE_PACK,
                status = RewardStatus.AVAILABLE,
                content = ChallengePack.SET_4
            )
        )

        rewards.add(
            Reward(
                rId = "16",
                name = "Title: President",
                name_fin = "Otsikko: Presidentti",
                image = "file:///android_asset/rewards_title_CN.jpg",
                tier = RewardTier.PRESIDENT_CARBON_NEUTRAL,
                points = 10000,
                type = RewardType.TITLE,
                status = RewardStatus.AVAILABLE,
                content = "President Carbon Neutral"
            )
        )

        rewards.add(
            Reward(
                rId = "17",
                name = "Avatar: Carbon Neutral",
                name_fin = "Avatar: Hiilineutraali",
                image = "file:///android_asset/rewards_profile_4.jpg",
                tier = RewardTier.PRESIDENT_CARBON_NEUTRAL,
                points = 10000,
                type = RewardType.PROFILE_PIC,
                status = RewardStatus.AVAILABLE,
                content = "file:///android_asset/rewards_profile_4.jpg"
            )
        )

        return rewards
    }
}