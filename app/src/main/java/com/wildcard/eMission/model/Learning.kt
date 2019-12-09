package com.wildcard.eMission.model

data class Learning(
    val rId: String,
    val name: String,
    val description: String,
    val tier: LearningTier
)

enum class LearningTier {
    BACKGROUND,
    HOME_APPLIANCES,
    TRAVELING
}