package com.mangata.sofatime.navigation.bottomNavigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PersonOutline
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import com.mangata.sofatime.R
import com.mangata.sofatime.navigation.Route

sealed class BottomNavItem(val title: Int, val icon: ImageVector, val route: String) {
    object Home : BottomNavItem(R.string.title_home, Icons.Default.Home, Route.HOME)
    object Profile : BottomNavItem(R.string.title_profile, Icons.Default.PersonOutline, Route.USER_PROFILE)
    object Search : BottomNavItem(R.string.title_search, Icons.Default.Search, Route.TV_SEARCH)

    companion object {
        val bottomNavItems = listOf(Home, Search ,Profile)
    }
}