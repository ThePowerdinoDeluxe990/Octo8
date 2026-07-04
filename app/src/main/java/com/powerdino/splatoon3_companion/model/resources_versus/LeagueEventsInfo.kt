package com.powerdino.splatoon3_companion.model.resources_versus


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LeagueEventsInfo(
    @SerialName("description")
    val description: String,
    @SerialName("subtitle")
    val subtitle: String,
    @SerialName("title")
    val title: String
)