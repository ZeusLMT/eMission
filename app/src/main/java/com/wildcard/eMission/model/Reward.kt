package com.wildcard.eMission.model

data class Reward (
    val rId: String,
    val name: String,
    val type: RewardType,
    val content: Any?
)

enum class RewardType {
    TITLE,
    CHALLENGE_PACK,
    ACTION
}