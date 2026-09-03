package com.powerdino.splatoon3_companion.ui.screens.routes.Events

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.powerdino.splatoon3_companion.model.AdditionalProp1
import com.powerdino.splatoon3_companion.model.resources_versus.ResourcesVersus
import com.powerdino.splatoon3_companion.ui.composables.EmptyApi
import com.powerdino.splatoon3_companion.ui.composables.MapCard
import com.powerdino.splatoon3_companion.ui.composables.SchedulesTimeComposables
import com.powerdino.splatoon3_companion.ui.composables.TextSchedule

@Composable
fun SplatfestScreen(
    splatfestData: Map<String, List<AdditionalProp1>>?,
    versusResources: ResourcesVersus,
){
    if (splatfestData == null){
        EmptyApi(
            stringResource(R.string.no_splatfest)
        )
    }else{
        if(splatfestData.isEmpty() ){
            EmptyApi(
                stringResource(R.string.no_splatfest)
            )
        }else{
            splatfestData.forEach { (string, props) ->
                if(props.isEmpty()){
                    EmptyApi(
                        stringResource(R.string.no_splatfest)
                    )
                }else{
                    LazyColumn {
                        items(props) { items ->

                            TextSchedule(
                                items.startTime,
                                items.endTime
                            )

                            SchedulesTimeComposables(
                                startsAt = items.startTime,
                                endsAt = items.endTime
                            )

                            Text(
                                text= versusResources.modes["FestRegular"].toString(),
                                style= MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(4.dp)
                            )

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                items.festRegular.stages.forEach {
                                    Box(
                                        modifier = Modifier.padding(horizontal = 4.dp)
                                            .weight(1f)
                                    ) {
                                        MapCard(
                                            mapName = versusResources.stages[it.toString()].toString(),

                                            mapImage = listOfMpMaps[it - 1].imageState
                                        )
                                    }

                                }
                            }
                            Spacer(
                                modifier = Modifier.padding(12.dp)
                            )


                            Text(
                                text= versusResources.modes["FestChallenge"].toString(),
                                style= MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(4.dp)
                            )

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                items.festChallenge.stages.forEach {
                                    Box(
                                        modifier = Modifier.padding(horizontal = 4.dp)
                                            .weight(1f)
                                    ) {
                                        MapCard(
                                            mapName = versusResources.stages[it.toString()].toString(),

                                            mapImage = listOfMpMaps[it - 1].imageState
                                        )
                                    }

                                }
                            }
                            Spacer(
                                modifier = Modifier.padding(12.dp)
                            )

                            if(!items.festTriColor.stages.isEmpty()){
                                Text(
                                    text= versusResources.modes["FestTriColor"].toString(),
                                    style= MaterialTheme.typography.titleLarge,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(4.dp)
                                )

                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    items.festTriColor.stages.forEach {
                                        Box(
                                            modifier = Modifier.padding(horizontal = 4.dp)
                                                .weight(1f)
                                        ) {
                                            MapCard(
                                                mapName = versusResources.stages[it.toString()].toString(),

                                                mapImage = listOfMpMaps[it - 1].imageState
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
            }
        }
    }
}