package com.powerdino.splatoon3_companion.ui.composables

import android.icu.util.Calendar
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.powerdino.splatoon3_companion.R
import kotlin.time.Clock
import kotlin.time.Duration.Companion.hours
import kotlin.time.Instant

@Composable
fun TextSchedule(
    startTime: String,
    endTime: String
){
    val startTime = Instant.parse(startTime)
    val endTime = Instant.parse(endTime)
    val currentTime = Clock.System.now()

    val textSchedule:String = if(
        currentTime in startTime .. endTime
        ){
        stringResource(R.string.current)
    }else{
        stringResource(R.string.next)
    }

    Text(
        text = textSchedule,
        style = MaterialTheme.typography.displaySmall,
        modifier = Modifier.padding(8.dp),
        fontWeight = FontWeight.Bold
    )
}