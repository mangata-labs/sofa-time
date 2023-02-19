package com.mangata.sofatime.navigation.bottomNavigation

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.mangata.core_ui.theme.*
import com.mangata.core_ui.util.drawLine

@Composable
fun BottomBar(
    navItems: List<BottomNavItem>,
    navController: NavHostController
) {
    BottomNavigation(
        modifier = Modifier
            .fillMaxWidth()
            .drawLine(),
        backgroundColor = MaterialTheme.colors.tabBarBackground
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination?.route

        navItems.forEach { screen ->
            AddItem(
                screen = screen,
                currentDestination = currentDestination,
                navController = navController
            )
        }
    }
}

@Composable
private fun RowScope.AddItem(
    screen: BottomNavItem,
    currentDestination: String?,
    navController: NavHostController
) {
    BottomNavigationItem(
        selectedContentColor = MaterialTheme.colors.primary,
        unselectedContentColor = MaterialTheme.colors.tabBarForeground,
        label = {
            Text(text = stringResource(screen.title))
        },
        icon = {
            Icon(imageVector = screen.icon, contentDescription = stringResource(screen.title))
        },
        selected = currentDestination == screen.route,
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.startDestinationId) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }
        }
    )
}