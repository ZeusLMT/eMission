package com.wildcard.eMission.ui.rewards

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wildcard.eMission.model.Reward
import com.wildcard.eMission.model.RewardStatus
import com.wildcard.eMission.model.RewardTier
import com.wildcard.eMission.model.RewardType

class RewardsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Rewards Fragment"
    }
    val text: LiveData<String> = _text

    val userPoints = MutableLiveData<Int>().apply { value = 5218 }

    val rewardsList = MutableLiveData<ArrayList<Reward>>()


    fun getRewardsList(): ArrayList<Reward> {
        val rewards = ArrayList<Reward>()

        rewards.add(
            Reward(
                rId = "1",
                name = "Private Green title",
                name_fin = "Y. Vihreaä otsikko",
                tier = RewardTier.PRIVATE_GREEN,
                type = RewardType.TITLE,
                points = 500,
                status = RewardStatus.AVAILABLE,
                content = "Private Green"
            )
        )

        rewards.add(
            Reward(
                rId = "2",
                name = "Private Green picture",
                name_fin = "Y. Vihreaä kuva",
                tier = RewardTier.PRIVATE_GREEN,
                type = RewardType.PROFILE_PIC,
                points = 500,
                status = RewardStatus.AVAILABLE,
                content = "New profile picture"
            )
        )

        rewards.add(
            Reward(
                rId = "3",
                name = "Corporal Bee title",
                name_fin = "Korp. Mehiläinen otsikko",
                tier = RewardTier.CORPORAL_BEE,
                type = RewardType.TITLE,
                points = 500,
                status = RewardStatus.AVAILABLE,
                content = "Corporal Bee"
            )
        )

        rewards.add(
            Reward(
                rId = "4",
                name = "Corporal Bee challenges",
                name_fin = "Korp. Mehilläinen haasteet",
                tier = RewardTier.CORPORAL_BEE,
                type = RewardType.CHALLENGE_PACK,
                points = 500,
                status = RewardStatus.AVAILABLE,
                content = "New Challenges Pack"
            )
        )

        rewards.add(
            Reward(
                rId = "5",
                name = "Sergeant Flower title",
                name_fin = "Kers. Kukka otsikko",
                tier = RewardTier.SERGEANT_FLOWER,
                type = RewardType.TITLE,
                points = 1000,
                status = RewardStatus.AVAILABLE,
                content = "Sergeant Flower"
            )
        )

        rewards.add(
            Reward(
                rId = "6",
                name = "Sergeant Flower background",
                name_fin = "Kers. Kukka taustakuva",
                tier = RewardTier.SERGEANT_FLOWER,
                points = 1000,
                type = RewardType.CHALLENGE_PACK,
                status = RewardStatus.AVAILABLE,
                content = "New background"
            )
        )

        rewards.add(
            Reward(
                rId = "7",
                name = "Captain Wood Title",
                name_fin = "Kapt. Puu otsikko",
                tier = RewardTier.CAPTAIN_WOOD,
                points = 2000,
                type = RewardType.TITLE,
                status = RewardStatus.AVAILABLE,
                content = "Captain Wood"
            )
        )

        rewards.add(
            Reward(
                rId = "8",
                name = "Captain Wood profile picture",
                name_fin = "Kapt. Puu profiilikuva",
                tier = RewardTier.CAPTAIN_WOOD,
                points = 3000,
                type = RewardType.PROFILE_PIC,
                status = RewardStatus.AVAILABLE,
                content = "New profile picture"
            )
        )

        rewards.add(
            Reward(
                rId = "9",
                name = "Captain Wood background",
                name_fin = "Kapt. Puu taustakuva",
                tier = RewardTier.CAPTAIN_WOOD,
                points = 3000,
                type = RewardType.BACKGROUND,
                status = RewardStatus.AVAILABLE,
                content = "New background"
            )
        )

        return rewards
    }
}