package com.powerdino.splatoon3_companion.ui.screens.routes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.powerdino.splatoon3_companion.R
import com.powerdino.splatoon3_companion.data.lists.listOfMpMaps
import com.powerdino.splatoon3_companion.model.Data
import com.powerdino.splatoon3_companion.model.resources_versus.ResourcesVersus
import com.powerdino.splatoon3_companion.ui.composables.EmptyApi
import com.powerdino.splatoon3_companion.ui.composables.MapCard
import com.powerdino.splatoon3_companion.ui.composables.SchedulesTimeComposables
import com.powerdino.splatoon3_companion.ui.composables.TextSchedule

@Composable
fun RegularBattlesScreen (
    splatoonNormal: Data,
    versusResources: ResourcesVersus
){
    if(splatoonNormal.normal.isEmpty()){
        EmptyApi(
            stringResource(R.string.no_general)
        )

    }
    Column (
        modifier = Modifier.testTag(
            stringResource(R.string.testTagRegular)
        )
    ){
        //Text("Normal Stages")
        LazyColumn {
            itemsIndexed(splatoonNormal.normal){index, items ->
                //Text(items.regular.rule)

                TextSchedule(
                    items.startTime,
                    items.endTime
                )

                SchedulesTimeComposables(
                    startsAt = items.startTime,
                    endsAt = items.endTime
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ){
                    items.regular.stages.forEach {
                        Box(
                            modifier = Modifier
                                .weight(1f)
                        ){
                            MapCard(
                                mapName = versusResources.stages[it.toString()].toString(),

                                mapImage = listOfMpMaps[it-1].imageState
                            )
                        }

                    }
                }
                Spacer(
                    modifier = Modifier.padding(12.dp)
                )
            }
        }
    }
}