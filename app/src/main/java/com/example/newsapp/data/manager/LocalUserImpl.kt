package com.example.newsapp.data.manager

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import com.example.newsapp.domain.manager.LocalUserManager
import kotlinx.coroutines.flow.Flow
import androidx.datastore.preferences.preferencesDataStore
import com.example.newsapp.util.Constant
import com.example.newsapp.util.Constant.USER_SETTING
import kotlinx.coroutines.flow.map


class LocalUserImpl(private val context: Context) : LocalUserManager {
    override suspend fun saveEntryPoint() {
        context.dataStore.edit { setting ->
            setting[PreferenceKeys.APP_ENTRY] = true
        }
    }

    override fun readEntry(): Flow<Boolean> {
        return context.dataStore.data.map {
            it[PreferenceKeys.APP_ENTRY] ?: false
        }
    }
}

private val readOnly = preferencesDataStore(name = USER_SETTING)

val Context.dataStore: DataStore<Preferences> by readOnly

private object PreferenceKeys {
    val APP_ENTRY = booleanPreferencesKey(Constant.APP_ENTRY)
}