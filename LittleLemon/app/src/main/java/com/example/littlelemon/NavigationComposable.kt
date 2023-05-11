package com.example.littlelemon

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun Navigation(navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = Destinations.OnBoard.getRoute()){
        composable(route = Destinations.OnBoard.getRoute()){
            OnBoarding(navHostController)
        }
        composable(route = Destinations.Home.getRoute()){
            HomeScreen()
        }
        composable(route = Destinations.Profile.getRoute()){
            ProfileScreen()
        }
    }
}