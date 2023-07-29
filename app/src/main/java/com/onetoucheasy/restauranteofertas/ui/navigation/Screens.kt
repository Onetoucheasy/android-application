package com.onetoucheasy.restauranteofertas.ui.navigation

sealed class Screens(val route: String) {
    object LoginScreen: Screens(SCREEN1_BASE_ROUTE)
    object MainScreen: Screens(SCREEN2_BASE_ROUTE)
    object RegisterScreen: Screens(SCREEN3_BASE_ROUTE)
    companion object{
        private const val SCREEN1_BASE_ROUTE = "LoginScreen"
        private const val SCREEN2_BASE_ROUTE = "MainScreen"
        private const val SCREEN3_BASE_ROUTE = "RegisterScreen"
    }
}