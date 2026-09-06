package com.powerdino.splatoon3_companion.ui.composables

import android.text.format.DateFormat
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CalendarToday
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalRippleConfiguration
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format
import kotlinx.datetime.format.char
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Instant

@Composable
fun SchedulesTimeComposables(
    startsAt:String,
    endsAt:String,
) {

    val context = LocalContext.current
    //val instant = Instant.parse(endsAt).toLocalDateTime(TimeZone.currentSystemDefault())

    val secondInstant = Instant.parse(startsAt).toLocalDateTime(TimeZone.currentSystemDefault())

    var currentTime: String = secondInstant.toString()

    val format12h = LocalDateTime.Format {
        year();char('-');monthNumber();char('-');day();
        char(' ')
        amPmHour();char(':');minute();
        char(' '); amPmMarker("AM", "PM")
    }
    if (!DateFormat.is24HourFormat(context)) {
        currentTime = secondInstant.format(
            format12h
        )
    }

    CompositionLocalProvider(LocalRippleConfiguration provides null) {
        AssistChip(
            onClick = {},
            modifier = Modifier.padding(
                start = 12.dp

            ),
            label = {
                Row {
                    Text(
                        text = currentTime
                            .replace("Z", "")
                            .replace("T", " ")
                    )
                }
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Rounded.CalendarToday,
                    contentDescription = "Calendar",
                    modifier = Modifier.padding(
                        end = 2.dp,
                        bottom = 2.dp
                    )
                )
            }
        )
    }
}