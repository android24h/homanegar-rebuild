package com.alirezahalvaei.homanegar.core.locale

import androidx.compose.runtime.staticCompositionLocalOf

enum class AppLanguage { FA, EN }

fun stringFor(language: AppLanguage): Strings= if (language== AppLanguage.FA) FaStrings else EnStrings
val LocalStrings = staticCompositionLocalOf<Strings> { FaStrings }

abstract class Strings {
    abstract val appName: String
    abstract val tagline: String
    abstract val loading: String
}

object FaStrings : Strings() {
    override val appName = "همانگار"
    override val tagline = "مدیریت ساده،کسب و کار بهتر"
    override val loading = "در حال آماده سازی..."

}

object EnStrings : Strings() {
    override val appName = "HomaNegar"
    override val tagline = "Simple management, better business"
    override val loading = "Getting things ready…"

}