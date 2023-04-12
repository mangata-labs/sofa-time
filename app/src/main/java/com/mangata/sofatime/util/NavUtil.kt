package com.mangata.sofatime.util

import androidx.compose.material.ScaffoldState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import coil.ImageLoader
import com.mangata.core_ui.screens.settings.SettingsScreen
import com.mangata.sofatime.navigation.Route
import com.mangata.sofatime.navigation.Screen
import com.mangata.tvshow_presentation.tvShowDetail.root.TvShowDetailScreen
import com.mangata.tvshow_presentation.tvShowHome.root.TvShowHomeScreen
import com.mangata.tvshow_presentation.tvShowSearch.root.TvShowSearchScreen
import com.mangata.tvshow_presentation.tvShowTracked.root.TvShowTrackedScreen
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf

fun NavGraphBuilder.addHomeRoute(
    imageLoader: ImageLoader,
    navController: NavController,
) {
    composable(
        route = Screen.Home.route
    ) {
        TvShowHomeScreen(
            viewModel = getViewModel(),
            imageLoader = imageLoader,
            onTvShowClick = { tvShowID ->
                navController.navigate("${Route.TV_ABOUT}/$tvShowID")
            },
            onSearchCardClick = {
                navController.navigate(Route.TV_SEARCH) {
                    popUpTo(navController.graph.startDestinationId) {
                        saveState = true
                    }
                    restoreState = true
                }
            },
        )
    }
}

fun NavGraphBuilder.addTvDetailsRoute(
    imageLoader: ImageLoader,
    navController: NavController,
    scaffoldState: ScaffoldState
) {
    composable(
        route = Screen.TvAbout.route,
        arguments = Screen.TvAbout.args
    ) {
        val tvShowID = it.arguments?.getInt("tvShowID")!!
        TvShowDetailScreen(
            imageLoader = imageLoader,
            viewModel = getViewModel(parameters = { parametersOf(tvShowID) }),
            scaffoldState = scaffoldState,
            onTvDetailClick = { id ->
                navController.navigate("${Route.TV_ABOUT}/$id")
            }
        )
    }
}

fun NavGraphBuilder.addSearchRoute(
    imageLoader: ImageLoader,
    navController: NavController,
) {
    composable(
        route = Screen.TvSearch.route,
        arguments = Screen.TvSearch.args
    ) {
        TvShowSearchScreen(
            imageLoader = imageLoader,
            viewModel = getViewModel(),
            onTvDetailClick = { tvShowID ->
                navController.navigate("${Route.TV_ABOUT}/$tvShowID")
            }
        )
    }
}

fun NavGraphBuilder.addTvTrackedRoute(
    imageLoader: ImageLoader,
    navController: NavController,
) {
    composable(
        route = Screen.TvTracked.route,
        arguments = Screen.TvTracked.args
    ) {
        TvShowTrackedScreen(
            imageLoader = imageLoader,
            viewModel = getViewModel(),
            onTvDetailClick = { tvShowID ->
                navController.navigate("${Route.TV_ABOUT}/$tvShowID")
            },
            onSettingsClick = {
                navController.navigate(Route.SETTINGS)
            }
        )
    }
}

fun NavGraphBuilder.addSettingsRoute(
    navController: NavController,
) {
    composable(
        route = Screen.Settings.route,
        arguments = Screen.Settings.args
    ) {
        SettingsScreen(
            viewModel = getViewModel(),
            onBackCLick = { navController.popBackStack() }
        )
    }
}