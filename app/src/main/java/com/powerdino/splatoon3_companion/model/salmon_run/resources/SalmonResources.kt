package com.powerdino.splatoon3_companion.model.salmon_run.resources


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SalmonResources(
    @SerialName("enemy")
    val enemy: Map<String,String>,
    @SerialName("events")
    val events: Map<String,String>,
    @SerialName("gear/clothes")
    val gearclothes: Map<String, String>,
    @SerialName("gear/head")
    val gearhead: Map<String, String>,
    @SerialName("gear/shoes")
    val gearshoes: Map<String,String>,
    @SerialName("locker")
    val locker: Map<String,String>,
    @SerialName("modes")
    val modes: Modes,
    @SerialName("stages")
    val stages: Map<String, String>,
    @SerialName("tides")
    val tides: Map<String,String>,
    @SerialName("weapons/main")
    val weaponsmain: Map<String, String>,

    @SerialName("weapons/special")
    val weaponsspecial: Map<String,String>
)