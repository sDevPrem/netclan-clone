package com.sdevprem.netclanclone.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.sdevprem.netclanclone.ui.onboarding.OnBoardingScreen
import com.sdevprem.netclanclone.ui.onboarding.ProfileEditScreen
import com.sdevprem.netclanclone.ui.onboarding.WelcomeScreen

sealed class Destination(
    val route: String
) {
    @Composable
    abstract fun Content(navController: NavController)

    object Welcome : Destination("welcome_screen") {
        @Composable
        override fun Content(navController: NavController) {
            WelcomeScreen(navController)
        }

        fun navigateToOnBoardingScreen(navController: NavController) {
            navController.navigate(OnBoarding.route) {
                popUpTo(Welcome.route) {
                    inclusive = true
                }
            }
        }
    }

    object OnBoarding : Destination("on_boarding") {
        @Composable
        override fun Content(navController: NavController) {
            OnBoardingScreen(navController)
        }

        fun navigateToProfileEditScreen(navController: NavController) {
            navController.navigate(ProfileEdit.route) {
                popUpTo(OnBoarding.route) {
                    inclusive = true
                }
            }
        }
    }

    object ProfileEdit : Destination("profile_edit") {
        @Composable
        override fun Content(navController: NavController) {
            ProfileEditScreen(navController)
        }

        fun navigateToExploreScreen(navController: NavController) {
            navController.navigate(BottomNavDestination.Explore.route) {
                popUpTo(ProfileEdit.route) {
                    inclusive = true
                }
            }
        }
    }
}

val destinations = listOf(
    Destination.OnBoarding,
    Destination.Welcome,
    Destination.ProfileEdit,
    BottomNavDestination.Explore,
    BottomNavDestination.Network,
    BottomNavDestination.Chat,
    BottomNavDestination.Contacts,
)
