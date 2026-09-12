package com.example.pmdassignment1.ui.theme

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun TravelBottomBar(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar {
        TravelBarItem(
            navController = navController,
            currentDestination = currentDestination,
            route = "destinations",
            label = "Destinations",
            icon = { Icon(Icons.Filled.Map, contentDescription = "Destinations") }
        )
        TravelBarItem(
            navController = navController,
            currentDestination = currentDestination,
            route = "logs",
            label = "Logs",
            icon = { Icon(Icons.Filled.MenuBook, contentDescription = "Logs") }
        )
    }
}

@Composable
fun RowScope.TravelBarItem(
    navController: NavHostController,
    currentDestination: NavDestination?,
    route: String,
    label: String,
    icon: @Composable () -> Unit
) {
    val selected = currentDestination?.route == route
    NavigationBarItem(
        selected = selected,
        onClick = {
            navController.navigate(route) {
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }
        },
        icon = icon,
        label = { Text(label) }
    )
}
