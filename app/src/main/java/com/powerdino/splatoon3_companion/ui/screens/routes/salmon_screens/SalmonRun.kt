package com.powerdino.splatoon3_companion.ui.screens.routes.salmon_screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import com.powerdino.splatoon3_companion.data.lists.SalmonRunStageImage
import com.powerdino.splatoon3_companion.model.salmon_run.Salmon
import com.powerdino.splatoon3_companion.model.salmon_run.resources.SalmonResources
import com.powerdino.splatoon3_companion.ui.composables.ModesAndBosses
import com.powerdino.splatoon3_companion.ui.composables.SchedulesTimeComposables
import com.powerdino.splatoon3_companion.ui.composables.TextSchedule
import com.powerdino.splatoon3_companion.ui.screens.routes.salmonComposables.SalmonMapCard

@Composable
fun SalmonRun(
    salmonSchedule: Salmon,
    salmonResources: SalmonResources,
){
    LazyColumn{
        item{

            Eggstra(
                eggstraSchedule = salmonSchedule.teamContest[0],
                salmonResources = salmonResources
            )
        }

        itemsIndexed(salmonSchedule.normal){ index, items ->

            TextSchedule(
                items.startTime,
                items.endTime
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ){
                SchedulesTimeComposables(
                    startsAt = items.startTime,
                    endsAt = items.endTime
                )

                ModesAndBosses(
                    items.bigBoss,
                    salmonResources.enemy[items.bigBoss].toString(),
                )
            }

            salmonResources.stages[items.stage.toString(),]?.let {
                val listOfWeapons = remember {
                    mutableStateListOf<String>()
                }

                items.weapons.forEach { weapons ->
                    when(weapons){
                        -1 ->listOfWeapons.add(
                            "Wildcard"
                        )
                        -2-> listOfWeapons.add(
                            "Golden"
                        )
                        else -> listOfWeapons.add(
                            salmonResources.weaponsmain[weapons.toString(),].toString()
                        )
                    }
                }

                SalmonMapCard(
                    it,
                    SalmonRunStageImage(items.stage.toString()),
                    weaponsList = items.weapons,
                    gearName = when(items.rewardGear.kind.lowercase()){
                        "clothes" -> salmonResources.gearclothes[items.rewardGear.id.toString()]
                        "shoes" -> salmonResources.gearshoes[items.rewardGear.id.toString()]
                        "head" -> salmonResources.gearhead[items.rewardGear.id.toString()]
                        else -> "Shirt"
                    }.toString(),
                    false
                )
            }
        }
    }
}