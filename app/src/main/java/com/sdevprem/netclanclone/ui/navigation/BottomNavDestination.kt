package com.sdevprem.netclanclone.ui.navigation

import androidx.annotation.DrawableRes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.sdevprem.netclanclone.R
import com.sdevprem.netclanclone.ui.home.explore.ExploreScreen

sealed class BottomNavDestination(
    route: String,
    @DrawableRes val icon: Int,
    val title: String
) : Destination(route) {

    companion object {
        val startDestination by lazy { Explore }
    }

    object Explore : BottomNavDestination(
        route = "explore",
        icon = R.drawable.ic_explore,
        title = "Explore"
    ) {
        @Composable
        override fun Content(navController: NavController) {
            ExploreScreen()
        }
    }

    object Network : BottomNavDestination(
        route = "network",
        icon = R.drawable.ic_network,
        title = "Network"
    ) {
        @Composable
        override fun Content(navController: NavController) {
            Text(text = title)
        }
    }

    object Chat : BottomNavDestination(
        route = "chat",
        icon = R.drawable.ic_chat,
        title = "Chat"
    ) {
        @Composable
        override fun Content(navController: NavController) {
            Text(text = title)
        }
    }

    object Contacts : BottomNavDestination(
        route = "contacts",
        icon = R.drawable.ic_contact,
        title = "Contacts"
    ) {
        @Composable
        override fun Content(navController: NavController) {
            Text(text = title)
        }
    }
}

val bottomNavDestination = listOf(
    BottomNavDestination.Explore,
    BottomNavDestination.Network,
    BottomNavDestination.Chat,
    BottomNavDestination.Contacts
)