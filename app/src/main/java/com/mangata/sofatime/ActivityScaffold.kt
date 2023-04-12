package com.mangata.sofatime

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import coil.ImageLoader
import com.mangata.sofatime.navigation.Screen
import com.mangata.sofatime.navigation.bottomNavigation.BottomBar
import com.mangata.sofatime.navigation.bottomNavigation.BottomNavItem
import com.mangata.sofatime.util.*

@Composable
fun ActivityScaffold(
    navController: NavHostController,
    scaffoldState: ScaffoldState,
    imageLoader: ImageLoader
) {
    val bottomBarState = rememberSaveable { (mutableStateOf(true)) }
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    when (navBackStackEntry?.destination?.route) {
        Screen.Home.route, Screen.TvSearch.route, Screen.TvTracked.route -> {
            bottomBarState.value = true
        }
        else -> bottomBarState.value = false
    }

    Scaffold(
        bottomBar = {
            AnimatedVisibility(
                visible = bottomBarState.value,
                enter = slideInVertically(initialOffsetY = { it }),
                exit = slideOutVertically(targetOffsetY = { it }),
                content = {
                    BottomBar(
                        navItems = BottomNavItem.bottomNavItems,
                        navController = navController
                    )
                }
            )
        },
        modifier = Modifier.fillMaxSize(),
        scaffoldState = scaffoldState
    ) { padding ->

        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            Modifier.padding(padding)
        ) {
            addHomeRoute(
                imageLoader = imageLoader,
                navController = navController
            )
            addTvDetailsRoute(
                imageLoader = imageLoader,
                navController = navController,
                scaffoldState = scaffoldState
            )
            addSearchRoute(
                imageLoader = imageLoader,
                navController = navController
            )
            addTvTrackedRoute(
                imageLoader = imageLoader,
                navController = navController
            )
            addSettingsRoute(
                navController = navController
            )
        }
    }
}