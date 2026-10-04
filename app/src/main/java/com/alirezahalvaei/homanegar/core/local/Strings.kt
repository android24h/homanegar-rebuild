package com.alirezahalvaei.homanegar.core.local

import androidx.compose.runtime.compositionLocalOf

val LocalStrings= compositionLocalOf<Strings> { EnString }

abstract class Strings{
    abstract val appName: String
    abstract val tagLine: String
    abstract val loading: String
}

object FaString: Strings(){
    override val appName= "همانگار"
    override val tagLine= "مدیریت ساده،کسب و کار بهتر"
    override val loading= "در حال آماده سازی..."

}

object EnString: Strings(){
    override val appName="HomaNegar"
    override val tagLine="Simple management, better business"
    override val loading="Getting things ready…"

}