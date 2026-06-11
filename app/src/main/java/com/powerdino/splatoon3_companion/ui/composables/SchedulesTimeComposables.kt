package com.powerdino.splatoon3_companion.ui.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CalendarToday
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SchedulesTimeComposables(
    startsAt:String,
    endsAt:String
) {

    Row(
        modifier = Modifier.padding(
            vertical = 0.5.dp,
            horizontal = 10.dp,
        ),
        verticalAlignment = Alignment.CenterVertically,

    ){
        Icon(
            imageVector = Icons.Rounded.CalendarToday,
            contentDescription = "Calendar",
            modifier = Modifier.padding(end = 2.dp,
                bottom = 2.dp)
        )
        TimeComposable(endsAt)
    }
}