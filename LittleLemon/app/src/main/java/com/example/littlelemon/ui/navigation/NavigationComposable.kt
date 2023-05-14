package com.example.littlelemon.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.littlelemon.data.PreferenceRepository
import com.example.littlelemon.ui.screen.HomeScreen
import com.example.littlelemon.ui.screen.OnBoarding
import com.example.littlelemon.ui.screen.ProfileScreen

@Composable
fun Navigation(navHostController: NavHostController, preferenceRepository: PreferenceRepository) {
    NavHost(
        navController = navHostController,
        startDestination =
        if (preferenceRepository.isUserLoggedIn())
            Destinations.Home.getRoute()
        else
            Destinations.OnBoard.getRoute()
    ) {
        composable(route = Destinations.OnBoard.getRoute()) {
            OnBoarding(navHostController, preferenceRepository)
        }
        composable(route = Destinations.Home.getRoute()) {
            HomeScreen(navHostController)
        }
        composable(route = Destinations.Profile.getRoute()) {
            ProfileScreen(navHostController)
        }
    }
}