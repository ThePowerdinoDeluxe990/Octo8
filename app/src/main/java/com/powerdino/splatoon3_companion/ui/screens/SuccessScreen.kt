package com.powerdino.splatoon3_companion.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.powerdino.splatoon3_companion.R
import com.powerdino.splatoon3_companion.model.Data
import com.powerdino.splatoon3_companion.model.resources_versus.ResourcesVersus
import com.powerdino.splatoon3_companion.model.salmon_run.Salmon
import com.powerdino.splatoon3_companion.model.salmon_run.resources.SalmonResources
import com.powerdino.splatoon3_companion.ui.screens.routes.Aboutscreen
import com.powerdino.splatoon3_companion.ui.screens.routes.BottomScreens
import com.powerdino.splatoon3_companion.ui.screens.routes.CompetitiveBattlesScreen
import com.powerdino.splatoon3_companion.ui.screens.routes.RegularBattlesScreen
import com.powerdino.splatoon3_companion.ui.screens.routes.SalmonRunScreen

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3ExpressiveApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SuccessScreen(
    splatoonData: Data,
    salmonResources: SalmonResources,
    salmonSchedules: Salmon,
    versusResources: ResourcesVersus
){
    val backStack = rememberNavBackStack(RegularBattlesScreen)

    var bottomSelected by rememberSaveable {
        mutableIntStateOf(0)
    }

    var disableBottomBar by remember { mutableStateOf(false) }
    var expanded by remember { mutableStateOf(false) }

    val bottomNavItems = listOf(
        BottomScreens.Versus,
        BottomScreens.Competitive,
        BottomScreens.Salmon
    )

    Scaffold (
        topBar = {
            if(!disableBottomBar) {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.primary,
                    ),
                    title = {
                        Text(
                            stringResource(R.string.app_name),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    },
                    actions = {
                        IconButton(
                            onClick = {
                                expanded = !expanded
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.MoreVert,
                                contentDescription = "Options"
                            )
                        }
                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false }
                        ) {
                            DropdownMenuItem(
                                text = { Text(stringResource(R.string.menu_about)) },
                                onClick = {
                                    backStack.add(Aboutscreen)
                                    disableBottomBar = true
                                }
                            )

                        }
                    }
                )
            }
        },
            bottomBar = {
                if(!disableBottomBar) {
                NavigationBar {

                    bottomNavItems.forEachIndexed { index, item ->
                        NavigationBarItem(
                            selected = bottomSelected == index,
                            onClick = {
                                bottomSelected = index
                                backStack.add(item.route)
                            },
                            label = {
                                Text(stringResource(item.title))
                            },
                            alwaysShowLabel = true,
                            icon = {
                                Icon(
                                    painter = if (index == bottomSelected) {
                                        painterResource(id = item.selectedIcon)
                                    } else painterResource(item.unselectedIcon),
                                    contentDescription = stringResource(item.title),
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                        )
                    }
                }
            }
        }
    ){ innerPadding ->
        NavDisplay(
            backStack=backStack,
            onBack ={
               backStack.removeLastOrNull()
                when(backStack.last()){
                    RegularBattlesScreen -> bottomSelected =0
                    CompetitiveBattlesScreen ->  bottomSelected = 1
                    SalmonRunScreen -> bottomSelected = 2
               }
             },
            entryProvider = entryProvider {
                entry<Aboutscreen> {
                    AboutScreen(
                        {backStack.removeLastOrNull()}
                    )
                }
                entry<RegularBattlesScreen>{
                    disableBottomBar= false
                    Box(
                        modifier = Modifier.padding(innerPadding)
                    ){
                        RegularBattlesScreen(
                            splatoonNormal = splatoonData,
                            versusResources = versusResources
                        )
                    }
                }
                entry<CompetitiveBattlesScreen>{
                    disableBottomBar= false
                    Box(
                        modifier = Modifier.padding(innerPadding)
                    ){
                        CompetitiveBattlesScreen(
                            splatoonNormal = splatoonData
                        )
                    }
                }
                entry<SalmonRunScreen>{

                    disableBottomBar= false
                    Box(
                        modifier = Modifier.padding(innerPadding)
                    ){
                       SalmonRunScreen(
                           salmonResources = salmonResources,
                           salmonSchedule = salmonSchedules
                       )
                    }
                }
            }
        )
    }
}