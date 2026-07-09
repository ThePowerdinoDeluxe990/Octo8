package com.powerdino.splatoon3_companion.ui.screens.routes

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.powerdino.splatoon3_companion.R
import com.powerdino.splatoon3_companion.model.AdditionalProp1
import com.powerdino.splatoon3_companion.model.challenge.EventItem
import com.powerdino.splatoon3_companion.model.resources_versus.ResourcesVersus
import com.powerdino.splatoon3_companion.ui.screens.routes.Events.ChallengeScreen
import com.powerdino.splatoon3_companion.ui.screens.routes.Events.SplatfestScreen

@Composable
fun EventScreen(
    splatfestData: Map<String, List<AdditionalProp1>>?,
    versusResources: ResourcesVersus,
    events:List<EventItem>
) {
    var eventScreens by rememberSaveable {
        mutableIntStateOf(0)
    }

    val eventButtons = listOf<String>(
        stringResource(R.string.events),
        versusResources.modes["League"].toString()
    )


    Column {
        PrimaryTabRow(selectedTabIndex = eventScreens) {
            eventButtons.forEachIndexed { index, string ->
                Tab(
                    selected = eventScreens == index,
                    onClick = { eventScreens = index },
                    text = { Text(string) }
                )
            }
        }

        when(eventScreens){
            0 -> SplatfestScreen(
                splatfestData,
                versusResources
            )
            1-> ChallengeScreen(
                versusResources,
                events
            )
        }

    }
}



@Composable
@Preview
private fun Preview(){

}