package com.powerdino.splatoon3_companion.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.powerdino.splatoon3_companion.ui.screens.ErrorScreen
import com.powerdino.splatoon3_companion.ui.screens.LoadingScreen
import com.powerdino.splatoon3_companion.ui.screens.SuccessScreen
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
            SuccessScreen(
                splatoonData = networkStateAndInfo.data,
                salmonResources = networkStateAndInfo.salmonResources,
                salmonSchedules = networkStateAndInfo.salmonSchedules,
                versusResources = networkStateAndInfo.versusResources
            )
        }
        is NetworkState.Loading -> {
            LoadingScreen()
        }
        is NetworkState.Error -> {
            ErrorScreen()
        }
    }

}


