package com.powerdino.splatoon3_companion.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Data(
    @SerialName("fest")
    val fest: Map<String, List<AdditionalProp1>>?=null,
    @SerialName("normal")
    val normal: List<Normal>
)