package com.powerdino.splatoon3_companion.model.resources_versus


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResourcesVersus(
    @SerialName("league/events")
    val leagueevents: Leagueevents,
    @SerialName("modes")
    val modes: Modes,
    @SerialName("rules")
    val rules: Map<String,String>,
    @SerialName("stages")
    val stages: Map<String, String>
)