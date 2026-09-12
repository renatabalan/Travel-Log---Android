package com.example.pmdassignment1.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.material3.Typography


private val LightColorScheme = lightColorScheme(
    primary = BlueSky,
    secondary = PurpleGrey40,
    background = SandBeige,
    surface = CloudWhite,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color.Black,
    onSurface = Color.Black,

)
private val DarkColorScheme = darkColorScheme(
    primary = OceanBlue,
    onPrimary = CloudWhite,
    secondary = SunsetOrange,
    onSecondary = CloudWhite,
    background = Color(0xFF121212),
    onBackground = CloudWhite,
    surface = Color(0xFF1E1E1E),
    onSurface = CloudWhite
)

@Composable
fun PMDAssignment1Theme(
    useDarkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = if (useDarkTheme) DarkColorScheme else LightColorScheme


    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}