package com.onetoucheasy.restauranteofertas.ui.scaffolds

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.onetoucheasy.restauranteofertas.ui.viewModels.LoginViewModel
import com.onetoucheasy.restauranteofertas.ui.viewModels.MainScreenViewModel

@Composable
fun MainScreen(viewModel: MainScreenViewModel, userType: String) {
    MainScreenContent()
}

@Composable
fun MainScreenContent() {
    Text("This is the main screen")
}

@Preview
@Composable
fun MainScreen_Preview() {
    MainScreenContent()
}