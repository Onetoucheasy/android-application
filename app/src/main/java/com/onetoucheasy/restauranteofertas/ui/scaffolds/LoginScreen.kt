package com.onetoucheasy.restauranteofertas.ui.scaffolds

import android.content.IntentSender.OnFinished
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.onetoucheasy.restauranteofertas.R
import com.onetoucheasy.restauranteofertas.ui.viewModels.LoginViewModel

@Composable
fun LoginScreen(viewModel: LoginViewModel, onLoginFinished:() -> (Unit)) {
    LoginScreenContent(true) { user, password ->
        viewModel.performLogin(user, password)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreenContent(isCompany: Boolean, onLoginClicked: (String, String) -> (Unit)) {
   Scaffold(
       modifier = Modifier
   ) {
       Box(modifier = Modifier.fillMaxSize()) {
           Image(painter = painterResource(R.mipmap.login_background),
               contentDescription = stringResource(id = R.string.login_background_image_description),
                modifier = Modifier.fillMaxSize()
               )

           Text(
               text = stringResource(id = R.string.login_customer_access),
               modifier = Modifier.padding(it)
           )


       }
   }
}

@Preview
@Composable
fun LoginScreen_Preview() {
    LoginScreenContent(true) {_,_ ->}
}