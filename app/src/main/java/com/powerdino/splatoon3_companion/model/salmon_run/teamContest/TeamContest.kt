package com.powerdino.splatoon3_companion.model.salmon_run.teamContest


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TeamContest(
    @SerialName("endTime")
    val endTime: String,
    @SerialName("phaseId")
    val phaseId: String,
    @SerialName("rareWeapons")
    val rareWeapons: List<Int>,
    @SerialName("rewards")
    val rewards: Rewards,
    @SerialName("specials")
    val specials: List<Int>,
    @SerialName("stage")
    val stage: Int,
    @SerialName("startTime")
    val startTime: String,
    @SerialName("waves")
    val waves: List<Wave>,
    @SerialName("weapons")
    val weapons: List<Int>
)