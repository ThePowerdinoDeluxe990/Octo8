package com.powerdino.splatoon3_companion.model.salmon_run.bigRun


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Rewards(
    @SerialName("bronze")
    val bronze: Bronze,
    @SerialName("gold")
    val gold: Gold,
    @SerialName("normal")
    val normal: Normal,
    @SerialName("silver")
    val silver: Silver
)