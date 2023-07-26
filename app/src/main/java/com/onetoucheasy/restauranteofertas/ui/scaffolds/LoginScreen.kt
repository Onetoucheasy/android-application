package com.onetoucheasy.restauranteofertas.ui.scaffolds

import android.content.IntentSender.OnFinished
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
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

@Composable
fun LoginScreenContent(isCompany: Boolean, onLoginClicked: (String, String) -> (Unit)) {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(painter = painterResource(R.mipmap.login_background), contentDescription = stringResource(
            id = R.string.login_background_image_description
        ) )

        Text(text = stringResource(id = R.string.login_customer_access))


    }
}

@Preview
@Composable
fun LoginScreen_Preview() {
    LoginScreenContent(true) {_,_ ->}
}