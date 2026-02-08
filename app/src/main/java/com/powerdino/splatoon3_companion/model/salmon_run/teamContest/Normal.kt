package com.powerdino.splatoon3_companion.model.salmon_run.teamContest


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Normal(
    @SerialName("minimumScore")
    val minimumScore: Int,
    @SerialName("reward")
    val reward: Int,
    @SerialName("topPercent")
    val topPercent: Int
)