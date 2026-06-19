package com.powerdino.splatoon3_companion.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.powerdino.splatoon3_companion.ui.screens.routes.settings.AboutScreen
import com.powerdino.splatoon3_companion.ui.screens.ErrorScreen
import com.powerdino.splatoon3_companion.ui.screens.LoadingScreen
import com.powerdino.splatoon3_companion.ui.screens.SettingScreen
import com.powerdino.splatoon3_companion.ui.screens.SuccessScreen
import com.powerdino.splatoon3_companion.ui.screens.routes.Aboutscreen
import com.powerdino.splatoon3_companion.ui.screens.routes.LibrariesScreen
import com.powerdino.splatoon3_companion.ui.screens.routes.MainScreen
import com.powerdino.splatoon3_companion.ui.screens.routes.SettingsScreen
import com.powerdino.splatoon3_companion.ui.screens.routes.settings.Libraries
import com.powerdino.splatoon3_companion.ui.viewModels.NetworkState
import com.powerdino.splatoon3_companion.ui.viewModels.SplatoonViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainEntryComposable(){
    val splatoonViewModel:SplatoonViewModel=
        viewModel(factory = SplatoonViewModel.Factory)
    val networkStateAndInfo: NetworkState =
        splatoonViewModel.splatoonNetworkState

    when(networkStateAndInfo){
        is NetworkState.Success -> {
            val mainBackStack = rememberNavBackStack(MainScreen)

            NavDisplay(
                backStack = mainBackStack,
                onBack = { mainBackStack.removeLastOrNull()},
                entryProvider = entryProvider {
                    entry<MainScreen> {
                        SuccessScreen(
                            splatoonData = networkStateAndInfo.data,
                            salmonResources = networkStateAndInfo.salmonResources,
                            salmonSchedules = networkStateAndInfo.salmonSchedules,
                            versusResources = networkStateAndInfo.versusResources,
                            mainBackStack = mainBackStack
                        )
                    }
                    entry<SettingsScreen>{
                        SettingScreen(
                            onClickBack = {mainBackStack.removeLastOrNull()},
                            backStack = mainBackStack
                        )
                    }
                    entry<Aboutscreen>{
                        AboutScreen(
                            onClickBack = {mainBackStack.removeLastOrNull()},
                            backStack = mainBackStack
                        )
                    }
                    entry<LibrariesScreen>{
                        Libraries(
                            onClickBack = {mainBackStack.removeLastOrNull()},
                        )
                    }
                }
            )
        }
        is NetworkState.Loading -> {
            LoadingScreen()
        }
        is NetworkState.Error -> {
            ErrorScreen(
                splatoonViewModel::getSplatoonData
            )
        }
    }
}


