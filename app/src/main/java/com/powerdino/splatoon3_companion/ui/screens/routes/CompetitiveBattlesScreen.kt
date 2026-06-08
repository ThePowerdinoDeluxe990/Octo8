package com.powerdino.splatoon3_companion.ui.screens.routes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.powerdino.splatoon3_companion.R
import com.powerdino.splatoon3_companion.model.Data
import com.powerdino.splatoon3_companion.ui.composables.EmptyApi
import com.powerdino.splatoon3_companion.ui.screens.routes.competitiveScreens.BankaraScreen
import com.powerdino.splatoon3_companion.ui.screens.routes.competitiveScreens.XBattlesScreen

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun CompetitiveBattlesScreen (
    splatoonNormal: Data
){
    var competitiveScreens by rememberSaveable {
        mutableIntStateOf(0)
    }

    var competitiveChecked by remember {
        mutableStateOf(false)
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
           SingleChoiceSegmentedButtonRow(
               modifier = Modifier
                   .fillMaxWidth()

           ) {
               competitiveButtons.forEachIndexed { index, string ->
                   val checked = index == competitiveScreens
                   SegmentedButton(
                       selected = checked,
                       label ={ Text(string)},
                       onClick = {
                           competitiveChecked != competitiveChecked
                           competitiveScreens = index
                       },
                       shape = SegmentedButtonDefaults.itemShape(
                           index = index,
                           count = competitiveButtons.size
                       ),
                   )
               }
           }
           HorizontalDivider(thickness = 2.dp)

           LazyColumn {
               itemsIndexed(splatoonNormal.normal) { index, items ->
                   when(competitiveScreens){
                       0 -> BankaraScreen(
                           index,items
                       )
                       1 -> XBattlesScreen(
                           index,items
                       )
                   }
               }
           }
       }
   }
}