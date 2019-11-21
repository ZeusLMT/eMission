package com.wildcard.eMission.ui.rewards

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wildcard.eMission.model.Reward
import com.wildcard.eMission.model.RewardTier
import com.wildcard.eMission.model.RewardType

class RewardsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Rewards Fragment"
    }
    val text: LiveData<String> = _text

    val userPoints = MutableLiveData<Int>().apply { value = 2500 }

    var rewardsList = MutableLiveData<ArrayList<Reward>>()

    fun getRewardsList() {
        val rewards = ArrayList<Reward>()

        rewards.add(
            Reward(
                rId = "1",
                name = "Private Green title",
                tier = RewardTier.PRIVATE_GREEN,
                type = RewardType.TITLE,
                content = "Private Green"
            )
        )

        rewards.add(
            Reward(
                rId = "2",
                name = "Private Green picture",
                tier = RewardTier.PRIVATE_GREEN,
                type = RewardType.PROFILE_PIC,
                content = "New profile picture"
            )
        )

        rewards.add(
            Reward(
                rId = "3",
                name = "Corporal Bee title",
                tier = RewardTier.CORPORAL_BEE,
                type = RewardType.TITLE,
                content = "Corporal Bee"
            )
        )

        rewards.add(
            Reward(
                rId = "4",
                name = "Corporal Bee challenges",
                tier = RewardTier.CORPORAL_BEE,
                type = RewardType.CHALLENGE_PACK,
                content = "New Challenges Pack"
            )
        )

        rewards.add(
            Reward(
                rId = "5",
                name = "Sergeant Flower title",
                tier = RewardTier.SERGEANT_FLOWER,
                type = RewardType.TITLE,
                content = "Sergeant Flower"
            )
        )

        rewards.add(
            Reward(
                rId = "6",
                name = "Sergeant Flower background",
                tier = RewardTier.SERGEANT_FLOWER,
                type = RewardType.CHALLENGE_PACK,
                content = "New background"
            )
        )

        rewardsList.value = rewards
    }
}