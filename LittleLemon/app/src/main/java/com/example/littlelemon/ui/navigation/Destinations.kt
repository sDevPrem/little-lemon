package com.example.littlelemon.ui.navigation

sealed interface Destinations{
    fun getRoute() : String

    object Home : Destinations {
        override fun getRoute(): String {
            return "home"
        }
    }
    object Profile : Destinations {
        override fun getRoute(): String {
            return "profile"
        }
    }
    object OnBoard : Destinations {
        override fun getRoute(): String {
            return "on_board"
        }
    }
}