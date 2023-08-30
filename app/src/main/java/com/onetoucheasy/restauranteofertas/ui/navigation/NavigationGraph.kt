package com.onetoucheasy.restauranteofertas.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.onetoucheasy.restauranteofertas.ui.scaffolds.LoginScreen
import com.onetoucheasy.restauranteofertas.ui.scaffolds.MainScreen
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

        composable(Screens.MainScreen.route, arguments = listOf(
            navArgument(Screens.MainScreen.ARG_USER_TYPE){
                this.type = NavType.StringType
            }
        )){
            val userType = it.arguments?.getString(Screens.MainScreen.ARG_USER_TYPE)
            if(userType != null){
                MainScreen(viewModel = mainViewModel, userType)
            }else{
                navController.navigateUp()
            }
        }
    }
}