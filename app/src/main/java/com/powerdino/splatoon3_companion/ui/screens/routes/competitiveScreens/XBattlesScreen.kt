package com.powerdino.splatoon3_companion.ui.screens.routes.competitiveScreens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.powerdino.splatoon3_companion.data.lists.listOfMpMaps
import com.powerdino.splatoon3_companion.model.Normal
import com.powerdino.splatoon3_companion.ui.composables.MapCard
import com.powerdino.splatoon3_companion.ui.composables.ModesAndBosses
import com.powerdino.splatoon3_companion.ui.composables.SchedulesTimeComposables
import com.powerdino.splatoon3_companion.ui.composables.TextSchedule

@Composable
fun XBattlesScreen(
    index: Int,
    items: Normal
)   {
    TextSchedule(
        items.startTime,
        items.endTime
    )
    Row(
        verticalAlignment = Alignment.CenterVertically
    ){
        SchedulesTimeComposables(
            startsAt = items.startTime,
            endsAt = items.endTime
        )

        ModesAndBosses(items.x.rule, null)
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        items.x.stages.forEach { it ->
            Box(
                modifier = Modifier
                    .padding(horizontal = 4.dp)
                    .weight(1f)
            ) {
                MapCard(
                    mapName = stringResource(listOfMpMaps[it - 1].nameState),
                    mapImage = listOfMpMaps[it - 1].imageState
                )
            }
        }
    }
}