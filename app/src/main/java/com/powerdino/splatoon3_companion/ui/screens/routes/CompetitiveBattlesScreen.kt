package com.powerdino.splatoon3_companion.ui.screens.routes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import com.powerdino.splatoon3_companion.R
import com.powerdino.splatoon3_companion.model.Data
import com.powerdino.splatoon3_companion.model.resources_versus.ResourcesVersus
import com.powerdino.splatoon3_companion.ui.composables.EmptyApi
import com.powerdino.splatoon3_companion.ui.screens.routes.competitiveScreens.BankaraScreen
import com.powerdino.splatoon3_companion.ui.screens.routes.competitiveScreens.XBattlesScreen

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun CompetitiveBattlesScreen (
    splatoonNormal: Data,
    resourcesVersus: ResourcesVersus
){
    var competitiveScreens by rememberSaveable {
        mutableIntStateOf(0)
    }

    val competitiveButtons = listOf<String>(
        stringResource(R.string.anarchy_battle),
        stringResource(R.string.x_battles)
    )

   if(splatoonNormal.normal.isEmpty()){
       EmptyApi(
           stringResource(R.string.no_general)
       )
   }else{
       Column {
           PrimaryTabRow(selectedTabIndex = competitiveScreens) {
               competitiveButtons.forEachIndexed { index, string ->
                   Tab(
                       selected = competitiveScreens == index,
                       onClick = { competitiveScreens = index },
                       text = { Text(string) }
                   )
               }
           }

           LazyColumn {
               itemsIndexed(splatoonNormal.normal) { index, items ->
                   when(competitiveScreens){
                       0 -> BankaraScreen(
                           index, items, resourcesVersus
                       )
                       1 -> XBattlesScreen(
                           index,items, resourcesVersus
                       )
                   }
               }
           }
       }
   }
}