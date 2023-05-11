package com.example.littlelemon

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.littlelemon.data.PreferenceRepository

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
            HomeScreen()
        }
        composable(route = Destinations.Profile.getRoute()) {
            ProfileScreen()
        }
    }
}