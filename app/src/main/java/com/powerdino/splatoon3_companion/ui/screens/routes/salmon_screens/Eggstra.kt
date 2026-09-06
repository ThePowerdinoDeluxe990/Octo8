package com.powerdino.splatoon3_companion.ui.screens.routes.salmon_screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.powerdino.splatoon3_companion.R
import com.powerdino.splatoon3_companion.data.lists.SalmonRunStageImage
import com.powerdino.splatoon3_companion.model.salmon_run.resources.SalmonResources
import com.powerdino.splatoon3_companion.model.salmon_run.teamContest.TeamContest
import com.powerdino.splatoon3_companion.ui.composables.SchedulesTimeComposables
import com.powerdino.splatoon3_companion.ui.composables.TextSchedule
import com.powerdino.splatoon3_companion.ui.screens.routes.salmonComposables.SalmonMapCard

@Composable
fun Eggstra(
    eggstraSchedule: TeamContest,
    salmonResources: SalmonResources
){
    Column() {
        TextSchedule(
            eggstraSchedule.startTime,
            eggstraSchedule.endTime
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ){
            Text(
                text= salmonResources.modes.teamContest,
                style= MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(
                    bottom = 8.dp,
                    top = 6.dp,
                    start = 12.dp,
                    end = 2.dp
                )
            )

            SchedulesTimeComposables(
                startsAt = eggstraSchedule.startTime,
                endsAt = eggstraSchedule.endTime
            )
        }
        salmonResources.stages[eggstraSchedule.stage.toString(),]?.let {
            val listOfWeapons = remember {
                mutableStateListOf<String>()
            }

            eggstraSchedule.weapons.forEach { weapons ->
                when (weapons) {
                    -1 -> listOfWeapons.add(
                        "Wildcard"
                    )

                    -2 -> listOfWeapons.add(
                        "Golden"
                    )

                    else -> listOfWeapons.add(
                        salmonResources.weaponsmain[weapons.toString(), ].toString()
                    )
                }
            }

            SalmonMapCard(
                it,
                SalmonRunStageImage(eggstraSchedule.stage.toString()),
                weaponsList = eggstraSchedule.weapons,
                gearName = eggstraSchedule.rewards.toString(),
                true
            )

            LazyRow(
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.fillMaxWidth()
                    .padding(12.dp)
            ) {
                itemsIndexed(eggstraSchedule.waves) { index,  it ->


                    Card (
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surfaceVariant,
                        ),
                        modifier = Modifier.padding(4.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(
                                12.dp
                            )
                        ) {
                            Text(
                                stringResource(R.string.wave) + " " + (index + 1).toString(),
                                fontWeight = FontWeight.SemiBold,
                                style = MaterialTheme.typography.bodyMedium
                            )
                            salmonResources.tides[it.tide]?.let { text ->
                                Text(
                                    text,
                                    style = MaterialTheme.typography.bodySmall
                                )
                            }
                            salmonResources.events[it.event]?.let { text ->
                                Text(
                                    text,
                                    style = MaterialTheme.typography.bodySmall
                                )
                            }
                        }
                    }
                }
            }
            Spacer(
                modifier = Modifier.padding(12.dp)
            )
        }
    }
}