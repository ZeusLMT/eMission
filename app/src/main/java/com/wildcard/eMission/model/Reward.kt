package com.wildcard.eMission.model

data class Reward (
    val rId: String,
    val name: String,
    val tier: RewardTier,
    val type: RewardType,
    val content: Any?
)

enum class RewardType {
    TITLE,
    CHALLENGE_PACK,
    ACTION,
    BACKGROUND,
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