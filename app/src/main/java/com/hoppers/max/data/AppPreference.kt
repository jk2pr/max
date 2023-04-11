package com.hoppers.max.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.hoppers.max.extensions.dataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Singleton

@Singleton
class AppPreference constructor(
    private val context: Context,
    private val prefs: DataStore<Preferences> = context.dataStore,
) {

    suspend fun setValue(key: String, value: String) =
        prefs.edit { it[stringPreferencesKey(key)] = value }

    suspend fun getValue(key: String) = prefs.data.map { it[stringPreferencesKey(key)] }.first()
}
