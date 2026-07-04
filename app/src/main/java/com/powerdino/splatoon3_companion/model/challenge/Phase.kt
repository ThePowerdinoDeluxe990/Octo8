package com.powerdino.splatoon3_companion.model.challenge


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Phase(
    @SerialName("endTime")
    val endTime: String,
    @SerialName("startTime")
    val startTime: String
)