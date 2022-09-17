package com.mangata.core_ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = LightRed,
    primaryVariant = DarkRed,
    onPrimary = SnowWhite,

    secondary = StarYellow,
    onSecondary = SnowWhite,

    background = SemiBlack,
    surface = SemiDarkGray,
)

private val LightColorPalette = lightColors(
    primary = MediumRed,
    primaryVariant = DarkRed,
    onPrimary = SnowWhite,

    secondary = StarYellow,
    onSecondary = SnowWhite,

    background = SnowWhite,
    surface = Color.White,

    /* Other default colors to override
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun SofaTimeTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}