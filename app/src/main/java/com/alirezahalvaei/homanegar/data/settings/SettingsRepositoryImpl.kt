package com.alirezahalvaei.homanegar.data.settings

import android.content.Context
import androidx.core.content.edit
import com.alirezahalvaei.homanegar.core.calendar.CalendarSystem
import com.alirezahalvaei.homanegar.core.locale.AppLanguage
import com.alirezahalvaei.homanegar.domain.model.AppSettings
import com.alirezahalvaei.homanegar.domain.model.ThemeMode

import com.alirezahalvaei.homanegar.domain.repository.SettingsRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.Locale
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SettingsRepositoryImpl @Inject constructor(
    @ApplicationContext context: Context
) : SettingsRepository {

    private val prefs = context.getSharedPreferences("homanegar_settings", Context.MODE_PRIVATE)

    private val _settings = MutableStateFlow(read())


    override val settings: StateFlow<AppSettings> = _settings.asStateFlow()

    override fun setLanguage(language: AppLanguage) {
        prefs.edit { putString(KEY_LANGUAGE, language.name) }
        _settings.value = read()
    }

    override fun setCalendar(calendar: CalendarSystem) {
        prefs.edit { putString(KEY_CALENDAR, calendar.name) }
        _settings.value = read()
    }

    override fun setThemeMode(mode: ThemeMode) {
        prefs.edit { putString(KEY_THEME, mode.name) }
        _settings.value = read()
    }

    private fun read(): AppSettings {

        val defaultLanguage =
            if (Locale.getDefault().language == "fa") AppLanguage.FA else AppLanguage.EN

        val language = prefs.getString(KEY_LANGUAGE, null)?.let {
            runCatching {
                AppLanguage.valueOf(it)
            }.getOrNull()
        } ?: defaultLanguage


        val calendar = prefs.getString(KEY_CALENDAR, null)?.let {
            runCatching {
                CalendarSystem.valueOf(it)
            }.getOrNull()
        } ?: if (language == AppLanguage.FA) CalendarSystem.JALALI else CalendarSystem.GREGORIAN

        val theme = prefs.getString(KEY_THEME, null)?.let {

            runCatching {
                ThemeMode.valueOf(it)
            }.getOrNull()
        } ?: ThemeMode.SYSTEM

        return AppSettings(language, calendar, theme)
    }

    private companion object {
        const val KEY_LANGUAGE = "language"
        const val KEY_CALENDAR = "calendar"
        const val KEY_THEME = "theme"
    }


}
