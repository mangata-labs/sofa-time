package com.mangata.sofatime.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

object Route {
    const val ON_BOARDING = "on_boarding"
    const val HOME = "home"
    const val TV_EXPLORE = "tv_explore"
    const val TV_ABOUT = "tv_about"
    const val TV_EPISODES = "tv_episodes"
    const val TV_UPCOMING = "tv_upcoming"
    const val USER_PROFILE = "user_profile"
}


sealed class Screen(val route: String, val args: List<NamedNavArgument>) {

    object Home : Screen(
        route = Route.HOME,
        args = emptyList()
    )

    object TvAbout : Screen(
        route = Route.TV_ABOUT + "/{tvShowID}",
        args = listOf(navArgument("tvShowID") {
            type = NavType.IntType
        })
    )

    object TvUpcoming : Screen(
        route = Route.TV_UPCOMING,
        args = emptyList()
    )

    object Profile : Screen(
        route = Route.USER_PROFILE,
        args = emptyList()
    )
}