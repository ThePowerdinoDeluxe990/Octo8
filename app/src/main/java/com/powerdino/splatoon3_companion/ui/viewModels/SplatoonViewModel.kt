package com.powerdino.splatoon3_companion.ui.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.powerdino.splatoon3_companion.SplatoonAppContainer
import com.powerdino.splatoon3_companion.data.SplatoonRepository
import com.powerdino.splatoon3_companion.model.Data
import com.powerdino.splatoon3_companion.model.challenge.EventItem
import com.powerdino.splatoon3_companion.model.resources_versus.ResourcesVersus
import com.powerdino.splatoon3_companion.model.salmon_run.Salmon
import com.powerdino.splatoon3_companion.model.salmon_run.resources.SalmonResources
import kotlinx.coroutines.launch

sealed interface NetworkState {
    data class Success(
        val data: Data,
        val salmonResources: SalmonResources,
        val salmonSchedules: Salmon,
        val versusResources: ResourcesVersus,
        val events: List<EventItem>
    ):NetworkState
    object Error:NetworkState
    object Loading:NetworkState
}

class SplatoonViewModel(private val splatoonRepository: SplatoonRepository): ViewModel() {
    var splatoonNetworkState:NetworkState by mutableStateOf( NetworkState.Loading)
        private set

    init {
        getSplatoonData()
    }

    fun getSplatoonData(){
        viewModelScope.launch {
            splatoonNetworkState= NetworkState.Loading
            splatoonNetworkState = try{
                NetworkState.Success(
                    splatoonRepository.getSplatoonData(),
                    salmonResources = splatoonRepository.getSalmonResources(),
                    salmonSchedules = splatoonRepository.getSalmonSchedule(),
                    versusResources = splatoonRepository.getVersusResources(),
                    events = splatoonRepository.getEvents()
                )
            }catch (_:java.io.IOException){
                NetworkState.Error
            }
        }
    }

    companion object{
        val Factory:ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application= (this[APPLICATION_KEY] as SplatoonAppContainer)
                val splatoonRepository = application.container.splatoonRepository
                SplatoonViewModel(splatoonRepository = splatoonRepository)
            }
        }
    }
}