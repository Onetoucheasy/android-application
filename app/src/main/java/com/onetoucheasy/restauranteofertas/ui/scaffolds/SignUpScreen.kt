package com.onetoucheasy.restauranteofertas.ui.scaffolds

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.onetoucheasy.restauranteofertas.R
import com.onetoucheasy.restauranteofertas.ui.theme.Black
import com.onetoucheasy.restauranteofertas.ui.theme.Gray
import com.onetoucheasy.restauranteofertas.ui.theme.MainYellow
import com.onetoucheasy.restauranteofertas.ui.theme.Transparent
import com.onetoucheasy.restauranteofertas.ui.viewModels.LoginState
import com.onetoucheasy.restauranteofertas.ui.viewModels.SignUpState
import com.onetoucheasy.restauranteofertas.ui.viewModels.SignUpViewModel

@Composable
fun SingUpScreen(viewModel: SignUpViewModel, onLogInClicked:() -> (Unit), onSignUpFinished:() -> (Unit)) {

    val signUpStatus by viewModel.signUpState.observeAsState()

    LaunchedEffect(signUpStatus){
        if(signUpStatus == SignUpState.SUCCESS){
            onSignUpFinished()
        }
    }

    signUpStatus?.let {
        SignUpScreenContent(it, onLogInClicked){ name, email, password, userType ->
            viewModel.performSignUp(name, email, password, userType)
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreenContent(signUpState: SignUpState, onLoginClicked: () -> (Unit), onRegisterlicked: (String, String, String, String) -> (Unit)) {
    Text("This is the registration screen ")
    val width = LocalConfiguration.current.screenWidthDp.dp

    var isCompany by remember {
        mutableStateOf(false)
    }
    var userType by remember {
        mutableStateOf("")
    }
    var name by remember {
        mutableStateOf("")
    }
    var surname by remember {
        mutableStateOf("")
    }
    var phoneNumber by remember {
        mutableStateOf("")
    }
    var email by remember {
        mutableStateOf("")
    }
    var emailValid by remember {
        mutableStateOf(false)
    }
    var password by remember {
        mutableStateOf("")
    }
    var repeatPassword by remember {
        mutableStateOf("")
    }
    var passwordValid by remember {
        mutableStateOf(false)
    }
    var invalidCredentials by remember{
        mutableStateOf(false)
    }

    Scaffold(
        modifier = Modifier
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(painter = painterResource(R.mipmap.sign_up_background),
                contentDescription = stringResource(id = R.string.login_background_image_description),
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillBounds
            )
            Text(
                text = if(isCompany){stringResource(id = R.string.sign_up_company)} else {stringResource(id = R.string.sign_up_customer)},
                modifier = Modifier
                    .width(230.dp)
                    .padding(start = 70.dp, top = 70.dp),
                maxLines = 2,
                style = MaterialTheme.typography.headlineLarge
            )

            Column(modifier = Modifier
                .align(Alignment.Center)
                .padding(top = 140.dp)) {
                Text(text = stringResource(id = R.string.login_authentication_error),
                    modifier = Modifier
                        .width(width * 4.0f / 5)
                        .padding(5.dp)
                        .alpha(
                            if (signUpState == SignUpState.FAILURE) {
                                1.0f
                            } else {
                                0.0f
                            }
                        ),
                    color = Color.Red
                )

                FormField(text = name, leadingIcon = Icons.Default.Person, onValueChange = {
                    name = it
                    invalidCredentials = false
                },
                    screenWidth = width,
                    isInvalidCredential = invalidCredentials
                ){
                    FormLabel(hint = stringResource(id = R.string.form_name_hint))
                }
                if (isCompany){
                    FormField(text = surname, leadingIcon = Icons.Default.Person, onValueChange = {
                        surname = it
                        invalidCredentials = false
                    },
                        screenWidth = width,
                        isInvalidCredential = invalidCredentials
                    ){
                        FormLabel(hint = stringResource(id = R.string.form_surname_hint))
                    }

                    FormField(text = phoneNumber, leadingIcon = Icons.Default.Person, onValueChange = {
                        phoneNumber = it
                        invalidCredentials = false
                    },
                        screenWidth = width,
                        isInvalidCredential = invalidCredentials
                    ){
                        FormLabel(hint = stringResource(id = R.string.form_phone_number_hint))
                    }
                }

                FormField(text = email, leadingIcon = Icons.Default.Email, onValueChange = {
                    email = it
                    invalidCredentials = false
                    //emailValid = it.contains("@")
                    emailValid = true
                },
                    screenWidth = width,
                    isInvalidCredential = invalidCredentials
                ){
                    FormLabel(hint = stringResource(id = R.string.form_email_hint))
                }

                FormField(text = password, leadingIcon = Icons.Default.Lock, trailingIcon = Icons.Default.Warning, isPassword = true, onValueChange = {
                    password = it
                    invalidCredentials = false
                    //  passwordValid = it.length > 8 && it.contains()
                    // passwordValid = it.length > 8 //TODO Add RegEx?
                    passwordValid = true
                }, screenWidth = width,
                    isInvalidCredential = invalidCredentials
                ){
                    FormLabel(hint = stringResource(id = R.string.form_password_hint))
                }

                FormField(text = repeatPassword, leadingIcon = Icons.Default.Lock, trailingIcon = Icons.Default.Warning, isPassword = true, onValueChange = {
                    repeatPassword = it
                    invalidCredentials = false
                    //  passwordValid = it.length > 8 && it.contains()
                    // passwordValid = it.length > 8 //TODO Add RegEx?
                    passwordValid = true
                }, screenWidth = width,
                    isInvalidCredential = invalidCredentials
                ){
                    FormLabel(hint = stringResource(id = R.string.form_repeat_password_hint))
                }

                Button(onClick = {
                    if(!emailValid || !passwordValid){
                        invalidCredentials = true
                    }
                    Log.d("SignUp", "Sign Up Started")
                    Log.d("SignUp", "$invalidCredentials")
                    if(!invalidCredentials){
                        Log.d("SignUp", "SignUp process started...")
                        onRegisterlicked(name, email, password, userType)
                    }

                },
                    modifier = Modifier
                        .width(width * 3.0f / 4)
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 20.dp), colors = ButtonDefaults.buttonColors(MainYellow, Black, Gray, Black)) {
                    Text(stringResource(id = R.string.sign_up_login_button))
                }

                Button(onClick = { onLoginClicked() },
                    modifier = Modifier
                        .width(width * 2.0f / 3)
                        .align(Alignment.CenterHorizontally),
                    colors = ButtonDefaults.buttonColors(Transparent,Black, Gray,Black)) {
                    Text(stringResource(id = R.string.sign_up_already_have_account))
                }
            }

            Button(onClick = { isCompany = !isCompany} , modifier = Modifier.align(Alignment.BottomEnd), colors = ButtonDefaults.buttonColors(
                Transparent, Black,  Gray, Black)) {
                Text(if(isCompany){stringResource(id = R.string.sign_up_customer)}else{stringResource(id = R.string.sign_up_company)})
            }
        }
    }
}


@Preview
@Composable
fun RegisterScreen_Preview() {
    SignUpScreenContent( SignUpState.NONE,{}) { _, _ , _ ,_->}
}