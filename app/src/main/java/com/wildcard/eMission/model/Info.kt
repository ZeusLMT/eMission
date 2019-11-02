package com.wildcard.eMission.model

data class Info (
    val iId: String,
    val name: String,
    val content: String,
    val tags: Array<String>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Info

        if (iId != other.iId) return false

        return true
    }

    override fun hashCode(): Int {
        return iId.hashCode()
    }
}