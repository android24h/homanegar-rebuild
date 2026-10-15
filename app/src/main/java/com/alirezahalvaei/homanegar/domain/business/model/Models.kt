package com.alirezahalvaei.homanegar.domain.business.model

import com.alirezahalvaei.homanegar.core.calander.CalendarSystem
import com.alirezahalvaei.homanegar.core.locale.AppLanguage

enum class ThemeMode { SYSTEM, LIGHT, DARK }

data class AppSettings(
    val language: AppLanguage,
    val calendar: CalendarSystem,
    val themeMode: ThemeMode
)