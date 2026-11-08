package com.powerdino.splatoon3_companion

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.powerdino.splatoon3_companion.data.AppContainer
import com.powerdino.splatoon3_companion.data.DefaultAppContainer
import com.powerdino.splatoon3_companion.settings.SettingsRepository

private const val SCREEN_PREFERENCE_NAME = "default_screen"
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = SCREEN_PREFERENCE_NAME
)
class SplatoonAppContainer:Application(){
    lateinit var container: AppContainer
    lateinit var settingsRepository: SettingsRepository


    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
        settingsRepository = SettingsRepository(dataStore)
    }
}