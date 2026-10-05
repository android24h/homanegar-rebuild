package com.alirezahalvaei.homanegar.presentation.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.lerp
import com.alirezahalvaei.homanegar.domain.business.BusinessPalette

/** رنگ‌های اختصاصی هر کسب‌وکار که خارج از MaterialTheme لازم داریم */
@Immutable
data class BrandColors(
    val primary: Color,
    val secondary: Color,
    val tertiary: Color,
    val isDark: Boolean
)

val LocalBrand = staticCompositionLocalOf {
    BrandColors(Color(0xFF4F46E5), Color(0xFF7C3AED), Color(0xFF06B6D4), false)
}

fun BusinessPalette.toBrand(dark: Boolean) = BrandColors(
    primary = Color(primary),
    secondary = Color(secondary),
    tertiary = Color(tertiary),
    isDark = dark
)

@Composable
fun HomaNegarTheme(
    palette: BusinessPalette,
    darkTheme: Boolean,
    content: @Composable () -> Unit
) {
    val brand = remember(palette, darkTheme) { palette.toBrand(darkTheme) }
    val p = brand.primary
    val s = brand.secondary
    val t = brand.tertiary

    val colorScheme = if (darkTheme) {
        val bg = lerp(p, Color(0xFF0C0E13), 0.93f)
        val surface = lerp(p, Color(0xFF14171E), 0.9f)
        darkColorScheme(
            primary = lerp(p, Color.White, 0.35f),
            onPrimary = lerp(p, Color.Black, 0.75f),
            primaryContainer = lerp(p, Color.Black, 0.45f),
            onPrimaryContainer = lerp(p, Color.White, 0.8f),
            secondary = lerp(s, Color.White, 0.35f),
            onSecondary = lerp(s, Color.Black, 0.75f),
            secondaryContainer = lerp(s, Color.Black, 0.5f),
            onSecondaryContainer = lerp(s, Color.White, 0.8f),
            tertiary = lerp(t, Color.White, 0.3f),
            tertiaryContainer = lerp(t, Color.Black, 0.5f),
            onTertiaryContainer = lerp(t, Color.White, 0.8f),
            background = bg,
            onBackground = Color(0xFFE8EAF0),
            surface = surface,
            onSurface = Color(0xFFE8EAF0),
            surfaceVariant = lerp(p, Color(0xFF232733), 0.85f),
            onSurfaceVariant = Color(0xFFA9AFBD),
            outline = lerp(p, Color(0xFF5A6070), 0.8f),
            outlineVariant = lerp(p, Color(0xFF2C313D), 0.85f),
            surfaceContainerLowest = bg,
            surfaceContainerLow = lerp(bg, surface, 0.5f),
            surfaceContainer = surface,
            surfaceContainerHigh = lerp(surface, Color.White, 0.04f),
            surfaceContainerHighest = lerp(surface, Color.White, 0.08f),
            error = Color(0xFFF87171)
        )
    } else {
        val bg = lerp(p, Color.White, 0.955f)
        lightColorScheme(
            primary = p,
            onPrimary = Color.White,
            primaryContainer = lerp(p, Color.White, 0.84f),
            onPrimaryContainer = lerp(p, Color.Black, 0.45f),
            secondary = s,
            onSecondary = Color.White,
            secondaryContainer = lerp(s, Color.White, 0.84f),
            onSecondaryContainer = lerp(s, Color.Black, 0.45f),
            tertiary = t,
            tertiaryContainer = lerp(t, Color.White, 0.84f),
            onTertiaryContainer = lerp(t, Color.Black, 0.45f),
            background = bg,
            onBackground = Color(0xFF151821),
            surface = Color.White,
            onSurface = Color(0xFF151821),
            surfaceVariant = lerp(p, Color.White, 0.92f),
            onSurfaceVariant = Color(0xFF5C6272),
            outline = lerp(p, Color(0xFFA3A8B5), 0.82f),
            outlineVariant = lerp(p, Color(0xFFE3E5EB), 0.9f),
            surfaceContainerLowest = Color.White,
            surfaceContainerLow = Color.White,
            surfaceContainer = lerp(p, Color.White, 0.97f),
            surfaceContainerHigh = lerp(p, Color.White, 0.95f),
            surfaceContainerHighest = lerp(p, Color.White, 0.92f),
            error = Color(0xFFDC2626)
        )
    }

    CompositionLocalProvider(LocalBrand provides brand) {
        MaterialTheme(
            colorScheme = colorScheme,
            content = content
        )
    }
}