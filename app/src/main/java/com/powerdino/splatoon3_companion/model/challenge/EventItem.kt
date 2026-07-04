package com.powerdino.splatoon3_companion.model.challenge


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EventItem(
    @SerialName("endTime")
    val endTime: String,
    @SerialName("eventId")
    val eventId: String,
    @SerialName("eventType")
    val eventType: String,
    @SerialName("phases")
    val phases: List<Phase>,
    @SerialName("rule")
    val rule: String,
    @SerialName("stages")
    val stages: List<Int>,
    @SerialName("startTime")
    val startTime: String
)