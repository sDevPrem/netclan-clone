package com.sdevprem.netclanclone.ui.common

import androidx.navigation.NavController
import com.sdevprem.netclanclone.ui.navigation.BottomNavDestination
import com.sdevprem.netclanclone.ui.navigation.Destination

fun NavController.navigateToBottomNavDestination(destination: Destination) {
    navigate(destination.route) {
        popUpTo(BottomNavDestination.startDestination.route) {
            saveState = true
        }
        restoreState = true
        launchSingleTop = true
    }
}