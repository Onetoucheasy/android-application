package com.onetoucheasy.restauranteofertas.ui.navigation

import com.onetoucheasy.restauranteofertas.ui.navigation.Screens.MainScreen.ARG_USER_TYPE

sealed class Screens(val route: String) {
    object LoginScreen: Screens(SCREEN1_BASE_ROUTE)
    object RegisterScreen: Screens(SCREEN3_BASE_ROUTE)
    object MainScreen: Screens(SCREEN3_BASE_ROUTE_TEMPLATE) {

        const val ARG_USER_TYPE = "userType"
        fun createRouteWithArgs(userType: String): String {
            return SCREEN3_BASE_ROUTE_TO_FORMAT.format(userType)
        }
    }
    companion object{
        private const val SCREEN1_BASE_ROUTE = "LoginScreen"
        private const val SCREEN2_BASE_ROUTE = "MainScreen"
        private const val SCREEN3_BASE_ROUTE = "RegisterScreen"
        private const val SCREEN3_BASE_ROUTE_TO_FORMAT = "$SCREEN2_BASE_ROUTE/%s"
        private const val SCREEN3_BASE_ROUTE_TEMPLATE = "$SCREEN2_BASE_ROUTE/{$ARG_USER_TYPE}"
    }
}