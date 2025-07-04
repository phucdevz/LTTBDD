package com.uth.myapplication.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

// Enum cho các theme
enum class AppTheme(val displayName: String) {
    LIGHT("Light"),
    DARK("Dark"),
    PURPLE("Purple"),
    BLUE("Blue");
}

@Composable
fun MyApplicationTheme(
    appTheme: AppTheme = AppTheme.LIGHT,
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when (appTheme) {
        AppTheme.LIGHT, AppTheme.PURPLE, AppTheme.BLUE -> getColorSchemeForTheme(appTheme)
        AppTheme.DARK -> if (dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val context = LocalContext.current
            dynamicDarkColorScheme(context)
        } else {
            DarkColorScheme
        }
    }
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

fun getColorSchemeForTheme(theme: AppTheme): androidx.compose.material3.ColorScheme {
    return when (theme) {
        AppTheme.LIGHT -> LightColorScheme
        AppTheme.DARK -> DarkColorScheme
        AppTheme.PURPLE -> lightColorScheme(
            primary = Pink40,
            secondary = PurpleGrey40,
            tertiary = Purple40
        )
        AppTheme.BLUE -> lightColorScheme(
            primary = androidx.compose.ui.graphics.Color(0xFF90CAF9), // Light Blue
            secondary = PurpleGrey40,
            tertiary = Pink40
        )
    }
}