package com.mangata.sofatime.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

object Route {
    const val HOME = "home"
    const val TV_ABOUT = "tv_about"
    const val TV_SEARCH = "tv_search"
    const val USER_PROFILE = "user_profile"
    const val WEB_VIEW = "web_view"
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

    object TVSearch: Screen(
        route = Route.TV_SEARCH,
        args = emptyList()
    )

    object Profile : Screen(
        route = Route.USER_PROFILE,
        args = emptyList()
    )

    object WebView : Screen(
        route = Route.WEB_VIEW + "/{webUrl}",
        args = listOf(navArgument("webUrl") {
            type = NavType.StringType
        })
    )
}