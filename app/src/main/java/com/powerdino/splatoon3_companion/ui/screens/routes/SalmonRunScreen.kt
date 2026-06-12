package com.powerdino.splatoon3_companion.ui.screens.routes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Tab
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
import com.powerdino.splatoon3_companion.model.salmon_run.Salmon
import com.powerdino.splatoon3_companion.model.salmon_run.resources.SalmonResources
import com.powerdino.splatoon3_companion.ui.screens.routes.salmon_screens.BigRun
import com.powerdino.splatoon3_companion.ui.screens.routes.salmon_screens.SalmonRun

@Composable
fun SalmonRunScreen(
    salmonResources: SalmonResources,
    salmonSchedule: Salmon
){

    var salmonScreens by rememberSaveable {
        mutableIntStateOf(0)
    }

    val salmonButtons = listOf<String>(
        stringResource(R.string.salmon_run),
        stringResource(R.string.big_run)
    )

    Column {
        PrimaryTabRow(selectedTabIndex = salmonScreens) {
            salmonButtons.forEachIndexed { index, string ->
                Tab(
                    selected = salmonScreens == index,
                    onClick = {salmonScreens= index},
                    text={Text(string)}
                )
            }
        }

        when(salmonScreens){
            0 -> SalmonRun(
                salmonSchedule,
                salmonResources
            )
            1 -> BigRun(
                salmonSchedule,
                salmonResources
            )
        }

    }
}