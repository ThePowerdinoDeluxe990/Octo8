package com.powerdino.splatoon3_companion.ui.composables

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@OptIn(ExperimentalTime::class)
@Composable
fun TimeComposable(
    time:String
){
    val instant = Instant.parse(time)

    Text(
        text = instant.toString()
            .substringAfterLast("T")
            .replace("Z",""),
    )
}