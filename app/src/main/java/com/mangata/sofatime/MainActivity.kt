package com.mangata.sofatime

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.rememberScaffoldState
import androidx.compose.ui.graphics.toArgb
import androidx.navigation.compose.rememberNavController
import coil.ImageLoader
import com.mangata.core_ui.theme.SofaTimeTheme
import com.mangata.sofatime.util.setLightStatusBars
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {

    private val imageLoader by inject<ImageLoader>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SofaTimeTheme {
                window.statusBarColor = MaterialTheme.colors.background.toArgb()
                window.setLightStatusBars(!isSystemInDarkTheme())

                val navController = rememberNavController()
                val scaffoldState = rememberScaffoldState()

                ActivityScaffold(
                    navController = navController,
                    scaffoldState = scaffoldState,
                    imageLoader = imageLoader
                )
            }
        }
    }
}
