package com.mangata.sofatime

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import coil.ImageLoader
import com.mangata.core_ui.profile_presentation.ProfileScreen
import com.mangata.sofatime.navigation.Route
import com.mangata.sofatime.navigation.bottomNavigation.BottomNavItem.Companion.bottomNavItems
import com.mangata.sofatime.navigation.bottomNavigation.BottomBar
import com.mangata.sofatime.navigation.Screen
import com.mangata.core_ui.theme.SofaTimeTheme
import com.mangata.tvshow_presentation.tvShowUpcoming.TvShowUpcomingScreen
import com.mangata.tvshow_presentation.tvShowDetail.TvShowDetailScreen
import com.mangata.tvshow_presentation.tvShowList.TvShowListScreen
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {

    private val imageLoader by inject<ImageLoader>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SofaTimeTheme {
                val navController = rememberNavController()
                val scaffoldState = rememberScaffoldState()
                val bottomBarState = rememberSaveable { (mutableStateOf(true)) }
                val navBackStackEntry by navController.currentBackStackEntryAsState()

                when (navBackStackEntry?.destination?.route) {
                    Screen.TvAbout.route -> {
                        bottomBarState.value = false
                    }
                    else -> bottomBarState.value = true
                }

                Scaffold(
                    bottomBar = {
                        AnimatedVisibility(
                            visible = bottomBarState.value,
                            enter = slideInVertically(initialOffsetY = { it }),
                            exit = slideOutVertically(targetOffsetY = { it }),
                            content = {
                                BottomBar(
                                    navItems = bottomNavItems,
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
                        startDestination = Screen.Home.route
                    ) {
                        composable(route = Screen.Home.route) {
                            TvShowListScreen(
                                scaffoldState = scaffoldState,
                                modifier = Modifier.padding(padding),
                                imageLoader = imageLoader,
                                onTvDetailClick = { tvShowID ->
                                    navController.navigate("${Route.TV_ABOUT}/$tvShowID")
                                }
                            )
                        }

                        composable(
                            route = Screen.TvAbout.route,
                            arguments = Screen.TvAbout.args
                        ) {
                            val tvShowID = it.arguments?.getInt("tvShowID")!!
                            TvShowDetailScreen(
                                tvShowID = tvShowID,
                                modifier = Modifier.padding(padding),
                            )
                        }

                        composable(
                            route = Screen.TvUpcoming.route,
                            arguments = Screen.TvUpcoming.args
                        ) {
                            TvShowUpcomingScreen(
                                modifier = Modifier.padding(padding)
                            )
                        }

                        composable(
                            route = Screen.Profile.route,
                            arguments = Screen.Profile.args
                        ) {
                            ProfileScreen(
                                modifier = Modifier.padding(padding)
                            )
                        }

                    }
                }
            }
        }
    }
}
