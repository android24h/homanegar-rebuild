package com.alirezahalvaei.homanegar.domain.repository

import com.alirezahalvaei.homanegar.core.calendar.CalendarSystem
import com.alirezahalvaei.homanegar.core.locale.AppLanguage
import com.alirezahalvaei.homanegar.domain.model.AppSettings
import com.alirezahalvaei.homanegar.domain.model.ThemeMode
import kotlinx.coroutines.flow.StateFlow

interface SettingsRepository {
    val settings: StateFlow<AppSettings>
    fun setLanguage(language: AppLanguage)
    fun setCalendar(calendar: CalendarSystem)
    fun setThemeMode(mode: ThemeMode)
}
