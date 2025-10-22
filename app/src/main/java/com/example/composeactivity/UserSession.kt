package com.example.composeactivity
import androidx.datastore.preferences.preferencesDataStore
import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.first
val Context.userDataStore by preferencesDataStore("user_prefs")


object UserSession {

    private lateinit var appContext: Context

    private val USER_ID_KEY = intPreferencesKey("user_id")

    private val Context.userDataStore by preferencesDataStore("user_session")

    fun init(context: Context) {
        appContext = context.applicationContext
    }


    suspend fun saveUserId(id: Int)  {
            appContext.userDataStore.edit { prefs ->
                prefs[USER_ID_KEY] = id
            }
    }

    fun getUserIdFlow(): Flow<Int?> {
        return appContext.userDataStore.data.map { prefs -> prefs[USER_ID_KEY] }
    }

    suspend fun getUserIdOnce(): Int? {
        val prefs = appContext.userDataStore.data.first()
        return prefs[USER_ID_KEY]
    }

    suspend fun clearUserId() {
        appContext.userDataStore.edit { it.clear() }
    }
}