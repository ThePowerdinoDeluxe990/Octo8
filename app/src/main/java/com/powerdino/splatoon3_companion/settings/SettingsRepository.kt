package com.powerdino.splatoon3_companion.settings

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class SettingsRepository (
    private val dataStore: DataStore<Preferences>
){
    val firstScreen : Flow<Int> = dataStore.data
        .catch {
            if(it is IOException) {
                Log.e(TAG, "Error reading preferences.", it)
                emit(emptyPreferences())
            } else {
                throw it
            }
        }
        .map { preferences ->
        preferences[FIRST_SCREEN] ?: 0
    }
    private companion object{
        val FIRST_SCREEN = intPreferencesKey("default_screen")
        const val TAG ="SettingsRepo"
    }

    suspend fun saveScreenPreference(firstScreen: Int) {
        dataStore.edit { preferences ->
            preferences[FIRST_SCREEN] = firstScreen

        }
    }
}