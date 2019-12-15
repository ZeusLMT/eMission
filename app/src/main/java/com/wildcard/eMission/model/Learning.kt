package com.wildcard.eMission.model

/**
This is for structuring needed data to be shown in Learning fragment.
 */
data class Learning(
    val rId: String,
    val name: String,
    val name_fin: String,
    val description: String,
    val description_fin: String,
    val tier: LearningTier
)

enum class LearningTier {
    BACKGROUND,
    HOME_APPLIANCES,
    TRAVELING
}