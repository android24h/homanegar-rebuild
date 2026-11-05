package com.alirezahalvaei.homanegar.presentation.app

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.font.FontVariation.Setting
import androidx.compose.ui.unit.LayoutDirection
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.alirezahalvaei.homanegar.core.locale.AppLanguage
import com.alirezahalvaei.homanegar.core.locale.LocalStrings
import com.alirezahalvaei.homanegar.core.locale.Strings
import com.alirezahalvaei.homanegar.core.locale.stringFor
import com.alirezahalvaei.homanegar.domain.business.BusinessCatalog
import com.alirezahalvaei.homanegar.domain.model.ThemeMode
import com.alirezahalvaei.homanegar.presentation.splash.SplashScreen
import com.alirezahalvaei.homanegar.presentation.theme.HomaNegarTheme

object Routes {
    const val SPLASH = "splash"
    const val HOME = "home"
}

@Composable
fun AppRoot(
    viewModel: AppViewModel = hiltViewModel()
) {


    val setting by viewModel.settings.collectAsState()

    val darkTheme = when (setting.themeMode) {
        ThemeMode.SYSTEM -> isSystemInDarkTheme()
        ThemeMode.LIGHT -> false
        ThemeMode.DARK -> true
    }






    CompositionLocalProvider(
        LocalStrings provides stringFor(setting.language),

        LocalLayoutDirection provides
                if (setting.language == AppLanguage.FA) LayoutDirection.Rtl else LayoutDirection.Ltr

    ) {
        HomaNegarTheme(
            palette = BusinessCatalog.defaultPalette,
            darkTheme = darkTheme
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

                        Column {
                            Text(
                                text = LocalStrings.current.appName,
                                modifier = Modifier.fillMaxWidth()
                            )
                            Button(
                                onClick = {
                                    viewModel.setLanguage(
                                        if (setting.language == AppLanguage.FA) AppLanguage.EN else AppLanguage.FA
                                    )
                                }
                            ) {
                                Text("FA / EN")
                            }
                            Button(
                                onClick = {
                                    viewModel.setTheme(
                                        when (setting.themeMode) {
                                            ThemeMode.SYSTEM -> ThemeMode.LIGHT
                                            ThemeMode.LIGHT -> ThemeMode.DARK
                                            ThemeMode.DARK -> ThemeMode.SYSTEM
                                        }
                                    )
                                }
                            ) {
                                Text(setting.themeMode.name)
                            }
                        }

                    }
                }
            }
        }
    }

}

