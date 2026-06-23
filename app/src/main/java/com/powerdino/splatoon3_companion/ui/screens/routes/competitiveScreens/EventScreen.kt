package com.powerdino.splatoon3_companion.ui.screens.routes.competitiveScreens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.powerdino.splatoon3_companion.model.events.EventItem
import com.powerdino.splatoon3_companion.model.resources_versus.ResourcesVersus

@Composable
fun EventScreen(
    resourcesVersus: ResourcesVersus,
    events:List<EventItem>
){
    Column() {
        events.forEach { element->
            Text(
                element.toString()
            )
        }
    }

}