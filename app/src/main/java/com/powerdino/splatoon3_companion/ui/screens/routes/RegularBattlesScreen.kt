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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Wifi
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.powerdino.splatoon3_companion.R
import com.powerdino.splatoon3_companion.data.lists.listOfMpMaps
import com.powerdino.splatoon3_companion.model.Data
import com.powerdino.splatoon3_companion.model.resources_versus.ResourcesVersus
import com.powerdino.splatoon3_companion.ui.composables.LinkButton
import com.powerdino.splatoon3_companion.ui.composables.MapCard
import com.powerdino.splatoon3_companion.ui.composables.SchedulesTimeComposables
import com.powerdino.splatoon3_companion.ui.composables.TextSchedule

@Composable
fun RegularBattlesScreen (
    splatoonNormal: Data,
    versusResources: ResourcesVersus
){
    val apiUrl = stringResource(R.string.api_url)
    val uriHandler = LocalUriHandler.current

    if(splatoonNormal.normal.isEmpty()){
        Column() {
            Text("This might be an error, check the api")
            LinkButton(
                onClick = {
                    uriHandler.openUri(apiUrl)
                },
                description = "API",
                text = "API",
                icon = Icons.Filled.Wifi
            )
        }

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
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ){
                    items.regular.stages.forEach {
                        Box(
                            modifier = Modifier.padding(horizontal = 4.dp)
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