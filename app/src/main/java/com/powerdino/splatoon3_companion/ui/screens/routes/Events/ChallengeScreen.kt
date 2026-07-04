package com.powerdino.splatoon3_companion.ui.screens.routes.Events

import android.text.format.DateFormat
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.powerdino.splatoon3_companion.data.lists.listOfMpMaps
import com.powerdino.splatoon3_companion.model.challenge.EventItem
import com.powerdino.splatoon3_companion.model.resources_versus.ResourcesVersus
import com.powerdino.splatoon3_companion.ui.composables.MapCard
import com.powerdino.splatoon3_companion.ui.composables.ModesAndBosses
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format
import kotlinx.datetime.format.char
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Instant

@Composable
fun ChallengeScreen(
    resourcesVersus: ResourcesVersus,
    events:List<EventItem>
){
    val context = LocalContext.current
    val format12h = LocalDateTime.Format {
        year();char('-');monthNumber();char('-');day();
        char(' ')
        amPmHour();char(':');minute();
        char(' '); amPmMarker("AM", "PM")
    }

    LazyColumn{
        items(events){element ->
            Column{
                OutlinedCard(
                    modifier = Modifier.padding(
                        top = 3.dp

                    )
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ){
                        Text(
                            text= resourcesVersus.leagueevents[element.eventType]?.title.toString(),
                            style= MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(
                                bottom = 8.dp,
                                top = 8.dp,
                                start = 8.dp,
                                end = 2.dp
                            )
                        )
                        Text(
                            text= resourcesVersus.leagueevents[element.eventType]?.subtitle.toString(),
                            style= MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(
                                bottom = 8.dp,
                                top = 8.dp,
                                start = 8.dp,
                                end = 2.dp
                            )
                        )

                        Text(
                            text= resourcesVersus.leagueevents[element.eventType]?.description.toString(),
                            style= MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(
                                bottom = 8.dp,
                                top = 8.dp,
                                start = 8.dp,
                                end = 2.dp
                            )
                        )
                    }
                }

                Spacer(
                    modifier = Modifier.padding(
                        top = 4.dp
                    )
                )

                ModesAndBosses(element.rule, resourcesVersus.rules[element.rule])
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ){
                element.stages.forEach{ stage->
                    Box(
                        modifier = Modifier.padding(horizontal = 4.dp)
                            .weight(1f)
                    ){
                        MapCard(
                            mapName = resourcesVersus.stages[stage.toString()].toString(),

                            mapImage = listOfMpMaps[stage-1].imageState
                        )
                    }

                }
            }

            element.phases.forEach {
                //val instant = Instant.parse(endsAt).toLocalDateTime(TimeZone.currentSystemDefault())

                val secondInstant = Instant.parse(it.startTime).toLocalDateTime(TimeZone.currentSystemDefault())

                var currentTime: String = secondInstant.toString()



                if (!DateFormat.is24HourFormat(context)) {
                    currentTime = secondInstant.format(
                        format12h
                    )
                }
                ListItem(
                    headlineContent = {
                        Text(
                            currentTime
                        )
                    },
                )
            }

            Spacer(
                modifier = Modifier.padding(12.dp)
            )

        }
    }
}



