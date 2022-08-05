package com.mangata.sofatime.util

import android.view.Window
import androidx.core.view.WindowCompat

fun Window.setLightStatusBars(isInLightMode: Boolean) {
    WindowCompat.getInsetsController(this, decorView).isAppearanceLightStatusBars = isInLightMode
}