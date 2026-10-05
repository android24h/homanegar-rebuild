package com.alirezahalvaei.homanegar


import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import com.alirezahalvaei.homanegar.domain.business.BusinessCatalog

import com.alirezahalvaei.homanegar.presentation.splash.SplashScreen
import com.alirezahalvaei.homanegar.presentation.theme.HomaNegarTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(statusBarStyle = SystemBarStyle.dark(Color.TRANSPARENT))
        setContent {
            HomaNegarTheme(
                palette = BusinessCatalog.defaultPalette,
                darkTheme = isSystemInDarkTheme()
            ) {
                SplashScreen()

            }

        }
    }
}



