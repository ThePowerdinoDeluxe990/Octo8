package com.powerdino.splatoon3_companion.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FestTriColor(
    @SerialName("rule")
    val rule: String,
    @SerialName("stages")
    val stages: List<Int>
)