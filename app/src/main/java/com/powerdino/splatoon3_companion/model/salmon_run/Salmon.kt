package com.powerdino.splatoon3_companion.model.salmon_run


import com.powerdino.splatoon3_companion.model.salmon_run.bigRun.BigRun
import com.powerdino.splatoon3_companion.model.salmon_run.teamContest.TeamContest
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Salmon(
    @SerialName("BigRun")
    val bigRun: List<BigRun>,
    @SerialName("Normal")
    val normal: List<SalmonNormal>,
    @SerialName("TeamContest")
    val teamContest:List<TeamContest>
)