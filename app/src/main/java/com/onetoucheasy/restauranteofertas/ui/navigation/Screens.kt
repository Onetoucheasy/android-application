package com.onetoucheasy.restauranteofertas.ui.navigation

import com.onetoucheasy.restauranteofertas.ui.navigation.Screens.DetailScreen.ARG_OFFER_ID
import com.onetoucheasy.restauranteofertas.ui.navigation.Screens.MainScreen.ARG_USER_TYPE
import com.onetoucheasy.restauranteofertas.ui.navigation.Screens.RestaurantScreen.ARG_RESTAURANT_ID

sealed class Screens(val route: String) {
    object LoginScreen: Screens(SCREEN1_BASE_ROUTE)
    object RegisterScreen: Screens(SCREEN3_BASE_ROUTE)
    object MainScreen: Screens(SCREEN3_BASE_ROUTE_TEMPLATE) {
        const val ARG_USER_TYPE = "userType"
        fun createRouteWithArgs(userType: String): String {
            return SCREEN3_BASE_ROUTE_TO_FORMAT.format(userType)
        }
    }
    object DetailScreen: Screens(SCREEN4_BASE_ROUTE_TEMPLATE) { // DetailScreen/offerId
        const val ARG_OFFER_ID = "offerId"
        fun createRouteWithArgs(id: String): String {
            return SCREEN4_BASE_ROUTE_TO_FORMAT.format(id)
        }
    }
    object RestaurantScreen: Screens(SCREEN5_BASE_ROUTE_TEMPLATE) {
        const val ARG_RESTAURANT_ID = "restaurantId"
        fun createRouteWithArgs(id: String): String {
            return SCREEN5_BASE_ROUTE_TO_FORMAT.format(id)
        }
    }
    companion object{
        private const val SCREEN1_BASE_ROUTE = "LoginScreen"
        private const val SCREEN2_BASE_ROUTE = "MainScreen"
        private const val SCREEN3_BASE_ROUTE = "RegisterScreen"
        private const val SCREEN3_BASE_ROUTE_TO_FORMAT = "$SCREEN2_BASE_ROUTE/%s"
        private const val SCREEN3_BASE_ROUTE_TEMPLATE = "$SCREEN2_BASE_ROUTE/{$ARG_USER_TYPE}"
        private const val SCREEN4_BASE_ROUTE = "DetailScreen"
        private const val SCREEN4_BASE_ROUTE_TO_FORMAT = "$SCREEN4_BASE_ROUTE/%s"
        private const val SCREEN4_BASE_ROUTE_TEMPLATE = "$SCREEN4_BASE_ROUTE/{$ARG_OFFER_ID}" // DetailScreen/offerId
        private const val SCREEN5_BASE_ROUTE = "RestaurantScreen"
        private const val SCREEN5_BASE_ROUTE_TO_FORMAT = "$SCREEN5_BASE_ROUTE/%s"
        private const val SCREEN5_BASE_ROUTE_TEMPLATE = "$SCREEN5_BASE_ROUTE/{$ARG_RESTAURANT_ID}"
    }
}