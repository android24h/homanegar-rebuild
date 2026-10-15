package com.alirezahalvaei.homanegar.presentation.app

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.alirezahalvaei.homanegar.core.calander.CalendarSystem
import com.alirezahalvaei.homanegar.core.locale.AppLanguage
import com.alirezahalvaei.homanegar.domain.business.BusinessCatalog
import com.alirezahalvaei.homanegar.domain.business.model.AppSettings
import com.alirezahalvaei.homanegar.domain.business.model.ThemeMode
import com.alirezahalvaei.homanegar.presentation.splash.SplashScreen
import com.alirezahalvaei.homanegar.presentation.theme.HomaNegarTheme

object Routes {
    const val SPLASH = "splash"
    const val HOME = "home"
}

@Composable
fun AppRoot() {
    HomaNegarTheme(
        palette = BusinessCatalog.defaultPalette,
        darkTheme = isSystemInDarkTheme()
    ) {
        val navController = rememberNavController()

        NavHost(
            navController = navController,
            startDestination = Routes.SPLASH
        ) {
            composable(Routes.SPLASH) {
                SplashScreen(
                    onFinished = {
                        navController.navigate(Routes.HOME) {
                            popUpTo(Routes.SPLASH) { inclusive = true }
                        }
                    }
                )
            }

            composable(Routes.HOME) {
                // موقت: در گام ۲ صفحه‌ی اصلی واقعی جایگزین می‌شود
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background),
                    contentAlignment = Alignment.Center
                ) {

                Text(
                    text = "صفحه اصلی"
                )
                }
            }
        }
    }
}

