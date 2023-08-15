package com.onetoucheasy.restauranteofertas.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.onetoucheasy.restauranteofertas.ui.scaffolds.LoginScreen
import com.onetoucheasy.restauranteofertas.ui.scaffolds.MainScreen
import com.onetoucheasy.restauranteofertas.ui.scaffolds.RegisterScreen

import com.onetoucheasy.restauranteofertas.ui.viewModels.LoginViewModel

@Composable
fun NavigationGraph(loginViewModel: LoginViewModel){
    //TODO Add ViewModels here as dependencies
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screens.LoginScreen.route){
        composable(Screens.LoginScreen.route){
            LoginScreen(viewModel = loginViewModel, {navController.navigate(Screens.RegisterScreen.route)}) {
                navController.navigate(Screens.MainScreen.route)
            }
        }

        composable(Screens.MainScreen.route){
            MainScreen()
        }

        composable(Screens.RegisterScreen.route){
            RegisterScreen()
        }
    }
}