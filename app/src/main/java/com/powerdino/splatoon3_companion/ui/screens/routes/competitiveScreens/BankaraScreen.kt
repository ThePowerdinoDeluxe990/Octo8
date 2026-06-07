package com.powerdino.splatoon3_companion.ui.screens.routes.competitiveScreens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.powerdino.splatoon3_companion.R
import com.powerdino.splatoon3_companion.data.lists.listOfMpMaps
import com.powerdino.splatoon3_companion.model.Normal
import com.powerdino.splatoon3_companion.ui.composables.MapCard
import com.powerdino.splatoon3_companion.ui.composables.SchedulesTimeComposables
import com.powerdino.splatoon3_companion.ui.composables.TextSchedule
import com.powerdino.splatoon3_companion.ui.composables.ModesAndBosses

@Composable
fun BankaraScreen(
    index: Int,
    items: Normal
){
    TextSchedule(
        items.startTime,
        items.endTime
    )
    SchedulesTimeComposables(
        startsAt = items.startTime,
        endsAt = items.endTime
    )
    Row{
        Text(
            text=stringResource(R.string.anarchy_open),
            style= MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(
                bottom = 8.dp,
                top = 8.dp,
                start = 8.dp,
                end = 2.dp
            )
        )
        ModesAndBosses(items.bankara.rule, null)
    }
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        items.bankara.stages.forEach { it ->
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
    Spacer(
        modifier = Modifier.padding(12.dp)
    )
    Row{
        Text(
            text="Anarchy Battle (Open)",
            style= MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(8.dp)
        )

        ModesAndBosses(items.bankaraOpen.rule, null)
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ){
        items.bankaraOpen.stages.forEach { it ->
            Box(
                modifier = Modifier
                    .padding(horizontal = 4.dp)
                    .weight(1f)
            ){
                MapCard(
                    mapName = stringResource(listOfMpMaps[it-1].nameState),
                    mapImage = listOfMpMaps[it-1].imageState
                )
            }
        }
    }
    Spacer(
        modifier = Modifier.padding(12.dp)
    )
}