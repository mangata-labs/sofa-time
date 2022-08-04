package com.mangata.core_ui.theme

import androidx.compose.material.Colors
import androidx.compose.ui.graphics.Color

val LightRed = Color(0xFFe5383b)
val MediumRed = Color(0xFFa4161a)
val DarkRed = Color(0xFF660708)

val StarYellow = Color(0xFFfca311)

val SnowWhite = Color(0xFFFBFBFB)
val LightGray = Color(0xFFDEE2E6)
val SemiLightGray = Color(0xFFADB5BD)
val MediumGray = Color(0xFF6C757D)
val SemiDarkGray = Color(0xFF212529)
val DarkGray = Color(0xFF0B090A)

val Colors.textPrimary: Color get() = if (isLight) SemiDarkGray else LightGray
val Colors.textPrimaryDim: Color get() = if (isLight) MediumGray else SemiLightGray
val Colors.componentBackground: Color get() = if (isLight) LightGray else SemiDarkGray