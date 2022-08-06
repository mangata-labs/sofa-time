package com.mangata.core_ui.theme

import androidx.compose.material.Colors
import androidx.compose.ui.graphics.Color

val LightRed = Color(0xFFdb5461)
val MediumRed = Color(0xFF990100)
val DarkRed = Color(0xFF660708)

val StarYellow = Color(0xFFfca311)

val SnowWhite = Color(0xFFF5F5F5)
val LightGray = Color(0xFFDEE2E6)
val SemiLightGray = Color(0xFFADB5BD)
val MediumGray = Color(0xFF6C757D)
val SemiDarkGray = Color(0xFF212529)
val DarkGray = Color(0xFF0B090A)

val Colors.NavigationBackground: Color get() = if (isLight) Color.White else Color.Black
val Colors.NavigationForeground: Color get() = if (isLight) SemiDarkGray else LightGray

val Colors.textPrimary: Color get() = if (isLight) SemiDarkGray else Color.White
val Colors.textPrimaryDim: Color get() = if (isLight) MediumGray else SemiLightGray
val Colors.componentBackground: Color get() = if (isLight) LightGray else SemiDarkGray