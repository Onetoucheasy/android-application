package com.onetoucheasy.restauranteofertas.ui.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.onetoucheasy.restauranteofertas.ui.navigation.Screens.DetailScreen.ARG_OFFER_ID
import com.onetoucheasy.restauranteofertas.ui.navigation.Screens.RestaurantScreen.ARG_RESTAURANT_ID
import com.onetoucheasy.restauranteofertas.ui.scaffolds.DetailScreen
import com.onetoucheasy.restauranteofertas.ui.scaffolds.LoginScreen
import com.onetoucheasy.restauranteofertas.ui.scaffolds.MainScreen
import com.onetoucheasy.restauranteofertas.ui.scaffolds.RestaurantScreen
import com.onetoucheasy.restauranteofertas.ui.scaffolds.SingUpScreen
import com.onetoucheasy.restauranteofertas.ui.viewModels.LoginViewModel
import com.onetoucheasy.restauranteofertas.ui.viewModels.MainScreenViewModel
import com.onetoucheasy.restauranteofertas.ui.viewModels.SignUpViewModel

@Composable
fun NavigationGraph(loginViewModel: LoginViewModel, signUpViewModel: SignUpViewModel, mainViewModel: MainScreenViewModel){
    //TODO Add ViewModels here as dependencies
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screens.LoginScreen.route){

        composable(Screens.LoginScreen.route){
            LoginScreen(viewModel = loginViewModel, { navController.navigate(Screens.RegisterScreen.route)}) {
                navController.navigate(Screens.MainScreen.createRouteWithArgs(it.toString()))
            }
        }
        composable(Screens.RegisterScreen.route){
            SingUpScreen(viewModel = signUpViewModel, {navController.navigate(Screens.LoginScreen.route)}){
                navController.navigate(Screens.LoginScreen.route)
            }
        }
        composable(Screens.MainScreen.route) {
            MainScreen(
                viewModel = mainViewModel,
                userType = "CUSTOMER",
                onOfferClick = { offerId: String -> navController.navigate(Screens.DetailScreen.createRouteWithArgs(offerId)) },
                onRestaurantClick = { restaurantId: String -> navController.navigate(Screens.RestaurantScreen.createRouteWithArgs(restaurantId)) }
            )
            Log.d("Tag","NavigationGraph > NavHost > composable > Screens.MainScreen.route")
        }
        composable(Screens.DetailScreen.route,
        arguments = listOf(
            navArgument(Screens.DetailScreen.ARG_OFFER_ID) {
                type = NavType.StringType
            }
        )
        ) {
            val id = it.arguments?.getString(Screens.DetailScreen.ARG_OFFER_ID)
            if (id != null) {
                DetailScreen(
                    viewModel = mainViewModel,
                    offerId = id,
                    onBackClick = { navController.navigate(Screens.MainScreen.route)}
                )
            } else {
                Log.d("Tag","id == null")
            }
        }
        composable(Screens.RestaurantScreen.route,
            arguments = listOf(
                navArgument(Screens.RestaurantScreen.ARG_RESTAURANT_ID) {
                    type = NavType.StringType
                }
            )
        ) {
            val id = it.arguments?.getString(Screens.RestaurantScreen.ARG_RESTAURANT_ID)
            if (id != null) {
                RestaurantScreen(
                    viewModel = mainViewModel,
                    id = id,
                    onBackClick = { navController.navigate(Screens.MainScreen.route)}
                )
            } else {
                Log.d("Tag","id == null")
            }
        }
    }
}