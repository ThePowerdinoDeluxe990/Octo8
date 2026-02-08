package com.powerdino.splatoon3_companion.model.salmon_run.teamContest


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Wave(
    @SerialName("event")
    val event: String,
    @SerialName("tide")
    val tide: String
)