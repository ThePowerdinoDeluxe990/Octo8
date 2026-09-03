package com.powerdino.splatoon3_companion.widgets

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.glance.GlanceModifier
import androidx.glance.appwidget.cornerRadius
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.padding
import androidx.glance.layout.size
import androidx.glance.layout.wrapContentSize
import com.powerdino.splatoon3_companion.data.lists.listOfMpMaps
import com.powerdino.splatoon3_companion.model.Normal
import com.powerdino.splatoon3_companion.model.resources_versus.ResourcesVersus
import com.powerdino.splatoon3_companion.widgets.composables.MapCardWidget
import com.powerdino.splatoon3_companion.widgets.composables.TimeWidget
import com.powerdino.splatoon3_companion.widgets.utils.timeFormatter

@Composable
fun WidgetRegular(
    maps:List<Normal>,
    resources: ResourcesVersus,
    width:Dp,
    height:Dp,
    context: Context
){

    maps.forEachIndexed { index, items ->
        if (index < 2) {
            val currentTime: String =
                timeFormatter(
                    items.startTime,
                    context
                )

            Column {
                Row(
                    modifier = GlanceModifier.padding(
                        bottom = 3.dp
                    ).fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    TimeWidget(
                        currentTime
                    )

                    Row(
                        modifier = GlanceModifier
                            .cornerRadius(6.dp)
                    ) {
                        items.regular.stages.forEach {
                            Row {
                                Box{
                                    MapCardWidget(
                                        mapName = resources.stages[it.toString()].toString(),
                                        mapImage = listOfMpMaps[it - 1].imageState,
                                        modifier = GlanceModifier
                                            .wrapContentSize()
                                            .size(
                                                height = height,
                                                width = width
                                            )
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}