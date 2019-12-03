package com.wildcard.eMission.model

data class Reward (
    val rId: String,
    val name: String,
    val image: String,
    val tier: RewardTier,
    val type: RewardType,
    val points: Int,
    var status: RewardStatus,
    val content: Any?

) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Reward

        if (rId != other.rId) return false
        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        var result = rId.hashCode()
        result = 31 * result + name.hashCode()
        return result
    }
}

enum class RewardType {
    TITLE,
    CHALLENGE_PACK,
    ACTION,
    THEME,
    PROFILE_PIC
}

enum class RewardTier {
    PRIVATE_GREEN,
    CORPORAL_BEE,
    SERGEANT_FLOWER,
    LIEUTENANT_TREE,
    CAPTAIN_WOOD,
    MAJOR_NATURE,
    COLONEL_ENVIRONMENT,
    GENERAL_CLIMATE,
    PRESIDENT_CARBON_NEUTRAL
}

enum class RewardStatus {
    AVAILABLE,
    UNAVAILABLE,
    CLAIMED
}