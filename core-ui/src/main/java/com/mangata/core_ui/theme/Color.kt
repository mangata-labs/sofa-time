package com.mangata.core_ui.theme

import androidx.compose.material.Colors
import androidx.compose.ui.graphics.Color

val SuperLightBlue = Color(0xFFd1e8f1)
val LightBlue = Color(0xFF9dcee2)
val MediumBlue = Color(0xFF4091c9)
val DarkBlue = Color(0xFF1368aa)

val StarYellow = Color(0xFFFCA311)

val SnowWhite = Color(0xFFF5F5F5)
val LightGray = Color(0xFFDEE2E6)
val SemiLightGray = Color(0xFFADB5BD)
val MediumGray = Color(0xFF6C757D)
val SemiDarkGray = Color(0xFF212529)
val DarkGray = Color(0xFF263238)
val SemiBlack = Color(0xFF0B090A)

// Tab Bar
val Colors.tabBarBackground: Color get() = if (isLight) Color.White else Color.Black
val Colors.tabBarForeground: Color get() = if (isLight) SemiDarkGray else LightGray

// Text
val Colors.textPrimary: Color get() = if (isLight) SemiDarkGray else Color.White
val Colors.textPrimaryDim: Color get() = if (isLight) MediumGray else SemiLightGray
val Colors.textPrimaryColored: Color get() = if (isLight) MediumBlue else LightBlue
val Colors.textPrimaryDimColored: Color get() = if (isLight) MediumGray else SnowWhite

// Cards
val Colors.cardBackground: Color get() = if (isLight) LightGray else DarkGray
val Colors.cardBackgroundColored: Color get() = if (isLight) SuperLightBlue else DarkBlue