package com.powerdino.splatoon3_companion.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Fest(
    @SerialName("additionalProp1")
    val additionalProp: List<AdditionalProp1>? = null,

    @SerialName( "additionalProp2")
    val additionalProp2: List<AdditionalProp1>? = null,
    @SerialName( "additionalProp3")
    val additionalProp3: List<AdditionalProp1>? = null,

    @SerialName("JUEA-00019")
    val splatFestList: List<AdditionalProp1>

    )