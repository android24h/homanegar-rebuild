package com.alirezahalvaei.homanegar.presentation.app

import androidx.lifecycle.ViewModel
import com.alirezahalvaei.homanegar.core.calendar.CalendarSystem
import com.alirezahalvaei.homanegar.core.locale.AppLanguage
import com.alirezahalvaei.homanegar.domain.model.AppSettings
import com.alirezahalvaei.homanegar.domain.model.ThemeMode
import com.alirezahalvaei.homanegar.domain.repository.SettingsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(val repository: SettingsRepository) : ViewModel() {

    val settings: StateFlow<AppSettings> = repository.settings

    fun setLanguage(language:AppLanguage)=repository.setLanguage(language)
    fun setCalendar(calendar: CalendarSystem)=repository.setCalendar(calendar)
    fun setTheme(theme:ThemeMode)=repository.setThemeMode(theme)

}