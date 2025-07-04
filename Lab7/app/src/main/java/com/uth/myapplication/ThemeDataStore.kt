package com.uth.myapplication

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.uth.myapplication.ui.theme.AppTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.first

val Context.themeDataStore by preferencesDataStore(name = "theme_prefs")

object ThemeDataStore {
    private val THEME_KEY = stringPreferencesKey("app_theme")

    fun getThemeFlow(context: Context): Flow<AppTheme> =
        context.themeDataStore.data.map { prefs ->
            val name = prefs[THEME_KEY] ?: AppTheme.LIGHT.name
            AppTheme.valueOf(name)
        }

    suspend fun setTheme(context: Context, theme: AppTheme) {
        context.themeDataStore.edit { prefs ->
            prefs[THEME_KEY] = theme.name
        }
    }

    suspend fun getThemeSync(context: Context): AppTheme {
        val prefs = context.themeDataStore.data
            .map { it[THEME_KEY] ?: AppTheme.LIGHT.name }
            .first()
        return AppTheme.valueOf(prefs)
    }
} 