package com.powerdino.splatoon3_companion.widgets.utils

import android.content.Context
import android.text.format.DateFormat
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format
import kotlinx.datetime.format.char
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Instant

fun timeFormatter(
    time:String,
    context: Context
):String {
    val secondInstant = Instant.parse(time).toLocalDateTime(TimeZone.currentSystemDefault())
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
    return currentTime
}