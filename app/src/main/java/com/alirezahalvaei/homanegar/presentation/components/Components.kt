package com.alirezahalvaei.homanegar.presentation.components

import androidx.compose.runtime.staticCompositionLocalOf
import com.alirezahalvaei.homanegar.core.calendar.CalendarSystem
import com.alirezahalvaei.homanegar.core.locale.AppLanguage
import com.alirezahalvaei.homanegar.domain.model.AppSettings
import com.alirezahalvaei.homanegar.domain.model.ThemeMode

val LocalSetting = staticCompositionLocalOf {
    AppSettings(
        language = AppLanguage.EN,
        calendar = CalendarSystem.JALALI,
        themeMode = ThemeMode.SYSTEM
    )


}

