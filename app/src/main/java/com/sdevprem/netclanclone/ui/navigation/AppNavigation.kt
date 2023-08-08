package com.sdevprem.netclanclone.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AppNavigation(
    navHostController: NavHostController,
    startDestination: Destination
) {
    NavHost(
        navController = navHostController,
        startDestination = startDestination.route
    ) {
        destinations.forEach { destination ->
            composable(route = destination.route) {
                destination.Content(navHostController)
            }
        }
    }
}