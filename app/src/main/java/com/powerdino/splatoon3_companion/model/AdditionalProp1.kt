package com.powerdino.splatoon3_companion.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AdditionalProp1(
    @SerialName("endTime")
    val endTime: String,
    @SerialName("FestChallenge")
    val festChallenge: FestChallenge,
    @SerialName("FestRegular")
    val festRegular: FestRegular,
    @SerialName("FestTriColor")
    val festTriColor: FestTriColor,
    @SerialName("phaseId")
    val phaseId: String,
    @SerialName("startTime")
    val startTime: String
)