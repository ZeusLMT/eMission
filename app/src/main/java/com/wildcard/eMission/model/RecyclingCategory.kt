package com.wildcard.eMission.model

data class RecyclingCategory(
    var name: String,
    var objects: List<Object>,
    var instructions: String,
    var notice: String?,
    var id: String
)
