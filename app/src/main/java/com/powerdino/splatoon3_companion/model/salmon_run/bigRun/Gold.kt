package com.powerdino.splatoon3_companion.model.salmon_run.bigRun


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Gold(
    @SerialName("minimumScore")
    val minimumScore: Int,
    @SerialName("reward")
    val reward: Int,
    @SerialName("topPercent")
    val topPercent: Int
)