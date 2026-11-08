package com.powerdino.splatoon3_companion.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.powerdino.splatoon3_companion.SplatoonAppContainer
import com.powerdino.splatoon3_companion.settings.SettingsRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


data class SettingsUiState(
    val screenLocation: Int
)
class SettingsViewModel(
    private val settingsRepository: SettingsRepository
): ViewModel(){

    val uiState: StateFlow<SettingsUiState> =
        settingsRepository.firstScreen.map { screenLocation ->
            SettingsUiState(screenLocation)
        }.stateIn(
            scope = viewModelScope,
            // Flow is set to emits value for when app is on the foreground
            // 5 seconds stop delay is added to ensure it flows continuously
            // for cases such as configuration change
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = runBlocking {
                SettingsUiState(
                    screenLocation = settingsRepository.firstScreen.first()
                )
            }
        )
    companion object{
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as SplatoonAppContainer)
                SettingsViewModel(application.settingsRepository)
            }
        }
    }

    fun selectDefaultScreen(screenLocation: Int){
        viewModelScope.launch {
            settingsRepository.saveScreenPreference(screenLocation)
        }
    }
}