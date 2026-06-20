package com.powerdino.splatoon3_companion.ui.composables

import android.content.res.Resources
import android.text.format.DateFormat
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CalendarToday
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Instant

@Composable
fun SchedulesTimeComposables(
    startsAt:String,
    endsAt:String,
) {
    val context = LocalContext.current
    val instant = Instant.parse(endsAt).toLocalDateTime(TimeZone.currentSystemDefault())

    var secondInstant = Instant.parse(startsAt).toLocalDateTime(TimeZone.currentSystemDefault())
    if (!DateFormat.is24HourFormat(context)) {
        secondInstant = Instant.parse(startsAt.replace("HH","hh")).toLocalDateTime(TimeZone.currentSystemDefault())
    }

    val currentTime = secondInstant
        .toString()
        .replace("Z","")
        .replace("T"," ")




    AssistChip(
        modifier = Modifier.padding(
            start = 4.dp
        ),
        onClick = {},
        label = {
            Row {
               Text(
                   text = currentTime
               )
            }
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Rounded.CalendarToday,
                contentDescription = "Calendar",
                modifier = Modifier.padding(end = 2.dp,
                    bottom = 2.dp)
            )
        }
    )
    Row(
        modifier = Modifier.padding(
            vertical = 0.5.dp,
            horizontal = 10.dp,
        ),
        verticalAlignment = Alignment.CenterVertically,
    ){}
}