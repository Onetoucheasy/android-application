package com.onetoucheasy.restauranteofertas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.onetoucheasy.restauranteofertas.ui.navigation.NavigationGraph
import com.onetoucheasy.restauranteofertas.ui.scaffolds.LoginScreen
import com.onetoucheasy.restauranteofertas.ui.theme.RestauranteOfertasTheme
import com.onetoucheasy.restauranteofertas.ui.viewModels.LoginViewModel
import com.onetoucheasy.restauranteofertas.ui.viewModels.MainScreenViewModel
import com.onetoucheasy.restauranteofertas.ui.viewModels.SignUpViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val loginViewModel: LoginViewModel by viewModels()
    private val signUpViewModel: SignUpViewModel by viewModels()
    private val mainViewModel: MainScreenViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RestauranteOfertasTheme {
               NavigationGraph(loginViewModel = loginViewModel, signUpViewModel= signUpViewModel, mainViewModel = mainViewModel)

            }
        }
    }
}
