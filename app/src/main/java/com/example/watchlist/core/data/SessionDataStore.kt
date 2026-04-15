package com.example.watchlist.core.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import com.example.watchlist.auth.domain.models.UserModel

val Context.dataStore by preferencesDataStore(name = "user_prefs")

class SessionDataStore(private val context: Context) {

    companion object {
        val USER_ID = stringPreferencesKey("user_id")
        val USER_NAME = stringPreferencesKey("user_name")
        val USER_EMAIL = stringPreferencesKey("user_email")
    }

    suspend fun saveUser(user: UserModel) {
        context.dataStore.edit { prefs ->
            prefs[USER_ID] = user.uid
            prefs[USER_EMAIL] = user.email ?: ""
            prefs[USER_NAME] = user.name ?: ""
        }
    }

    val userFlow: Flow<UserModel?> = context.dataStore.data.map { prefs ->
        val id = prefs[USER_ID]
        val name = prefs[USER_NAME]
        val email = prefs[USER_EMAIL]
        if (id != null) UserModel(id, name?.ifEmpty { null }, email?.ifEmpty { null }) else null
    }

    suspend fun clearUser() {
        context.dataStore.edit { prefs ->
            prefs.clear()
        }
    }
}