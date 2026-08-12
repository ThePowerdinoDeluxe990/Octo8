package com.powerdino.splatoon3_companion.ui.screens

import androidx.compose.animation.ContentTransform
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
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
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.powerdino.splatoon3_companion.R
import com.powerdino.splatoon3_companion.model.Data
import com.powerdino.splatoon3_companion.model.challenge.EventItem
import com.powerdino.splatoon3_companion.model.resources_versus.ResourcesVersus
import com.powerdino.splatoon3_companion.model.salmon_run.Salmon
import com.powerdino.splatoon3_companion.model.salmon_run.resources.SalmonResources
import com.powerdino.splatoon3_companion.ui.screens.routes.BottomScreens
import com.powerdino.splatoon3_companion.ui.screens.routes.CompetitiveBattlesScreen
import com.powerdino.splatoon3_companion.ui.screens.routes.EventScreen
import com.powerdino.splatoon3_companion.ui.screens.routes.RegularBattlesScreen
import com.powerdino.splatoon3_companion.ui.screens.routes.SalmonRunScreen
import com.powerdino.splatoon3_companion.ui.screens.routes.SettingsScreen
import com.powerdino.splatoon3_companion.ui.viewModels.SplatoonViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun SuccessScreen(
    splatoonData: Data,
    salmonResources: SalmonResources,
    salmonSchedules: Salmon,
    versusResources: ResourcesVersus,
    eventSchedules: List<EventItem>,
    mainBackStack: NavBackStack<NavKey>,
    viewModel: SplatoonViewModel
){
    val backStack = rememberNavBackStack(RegularBattlesScreen)

    var expanded by remember { mutableStateOf(false) }

    val bottomNavItems = listOf(
        BottomScreens.Versus,
        BottomScreens.Competitive,
        BottomScreens.Salmon,
        BottomScreens.Events
    )

    Scaffold (
        topBar = {
            TopAppBar(
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
                            text = { Text(stringResource(R.string.settings_menu)) },
                            onClick = {
                                mainBackStack.add(SettingsScreen)
                                expanded = false
                            }
                        )
                        DropdownMenuItem(
                            text = { Text(stringResource(R.string.reload)) },
                            onClick = {
                                viewModel.getSplatoonData()
                                expanded = false
                            }
                        )
                    }
                }
            )
        },
        bottomBar = {
            NavigationBar {
                bottomNavItems.forEach { item ->
                    NavigationBarItem(
                        selected = backStack.last() == item.route,
                        onClick = {
                            backStack.add(item.route)
                        },
                        label = {
                            Text(stringResource(item.title))
                        },
                        alwaysShowLabel = true,
                        icon = {
                            Icon(
                                painterResource(item.unselectedIcon),
                                contentDescription = stringResource(item.title),
                                modifier = Modifier.size(24.dp)
                            )
                        },

                    )
                }
            }
        }
    ){ innerPadding ->
        NavDisplay(
            backStack=backStack,
            transitionSpec = {
                ContentTransform(
                    targetContentEnter = fadeIn(tween(300)),
                    initialContentExit = fadeOut(tween(300)),
                )
             },
            popTransitionSpec = {
                ContentTransform(
                    targetContentEnter = fadeIn(tween(300)),
                    initialContentExit = fadeOut(tween(300)),
                )
            },
            predictivePopTransitionSpec = {
                ContentTransform(
                    targetContentEnter = fadeIn(tween(300)),
                    initialContentExit = fadeOut(tween(300)),
                )
            },
            /*
                    transitionSpec = {
                slideInHorizontally { it } + fadeIn() togetherWith
                        slideOutHorizontally { -it } + fadeOut()
            },
            popTransitionSpec = {
                slideInHorizontally { -it } + fadeIn() togetherWith
                        slideOutHorizontally { it } + fadeOut()
            },
            predictivePopTransitionSpec = {
                slideInHorizontally { -it } + fadeIn() togetherWith
                        slideOutHorizontally { it } + fadeOut()
            },

             */
            onBack ={
                backStack.removeLastOrNull()
            },
            entryProvider = entryProvider {
                entry<RegularBattlesScreen>{
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
                    Box(
                        modifier = Modifier.padding(innerPadding)
                    ){
                        CompetitiveBattlesScreen(
                            splatoonNormal = splatoonData,
                            versusResources,
                        )
                    }
                }
                entry<SalmonRunScreen>{

                    Box(
                        modifier = Modifier.padding(innerPadding)
                    ){
                       SalmonRunScreen(
                           salmonResources = salmonResources,
                           salmonSchedule = salmonSchedules
                       )
                    }
                }

                entry<EventScreen>{
                    Box(
                        modifier = Modifier.padding(innerPadding)
                    ){
                        EventScreen(
                            splatfestData = splatoonData.fest,
                            versusResources = versusResources,
                            eventSchedules
                        )
                    }
                }
            }
        )
    }
}