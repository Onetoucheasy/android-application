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
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.onetoucheasy.restauranteofertas.R
import com.onetoucheasy.restauranteofertas.ui.theme.Black
import com.onetoucheasy.restauranteofertas.ui.theme.Gray
import com.onetoucheasy.restauranteofertas.ui.theme.MainYellow
import com.onetoucheasy.restauranteofertas.ui.theme.Transparent
import com.onetoucheasy.restauranteofertas.ui.viewModels.LoginViewModel
import androidx.compose.runtime.livedata.observeAsState

//TODO add internet permission
@Composable
fun LoginScreen(viewModel: LoginViewModel,onRegisterClicked: () -> (Unit) , onLoginFinished:() -> (Unit)) {

    val loginStatus by viewModel.loginState.observeAsState()



    LaunchedEffect(loginStatus){
        if(loginStatus == true){
            onLoginFinished()
        }
    }

    LoginScreenContent(onRegisterClicked) { user, password ->
        viewModel.performLogin(user, password)
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreenContent(onRegisterClicked: () -> (Unit),onLoginClicked: (String, String) -> (Unit)) {

    var isCompany by remember {
        mutableStateOf(false)
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
    var passwordValid by remember {
        mutableStateOf(false)
    }
    Scaffold(
       modifier = Modifier
   ) {
       Box(modifier = Modifier.fillMaxSize()) {
           Image(painter = painterResource(R.mipmap.login_background),
               contentDescription = stringResource(id = R.string.login_background_image_description),
                modifier = Modifier.fillMaxSize()
               )
            //TODO Add dynamic width and padding values depending on the screen size
           Text(
               text = if(isCompany){stringResource(id = R.string.login_company_access)} else {stringResource(id = R.string.login_customer_access)},
               modifier = Modifier
                   .width(230.dp)
                   .padding(start = 70.dp, top = 70.dp),
               maxLines = 2,
               style = MaterialTheme.typography.headlineLarge
           )

           Column(modifier = Modifier.align(Alignment.Center)) {

               FormField(text = email, leadingIcon = Icons.Default.Email, onValueChange = {
                   email = it
                   emailValid = it.contains("@")
               }){
                   FormLabel(hint = stringResource(id = R.string.login_email_hint))
               }

               FormField(text = password, leadingIcon = Icons.Default.Lock, trailingIcon = Icons.Default.Warning, isPassword = true, onValueChange = {
                   password = it
                   //  passwordValid = it.length > 8 && it.contains()
                   passwordValid = it.length > 8 //TODO Add RegEx?
               }){
                   FormLabel(hint = stringResource(id = R.string.login_password_hint))

               }

               //TODO add access button and the button to turn the login form to profesional form and viceversa
               Button(onClick = {
                   Log.d("Token", "Login access started")
                   onLoginClicked(email,password)
                   },
                   modifier = Modifier.padding(top = 50.dp), colors = ButtonDefaults.buttonColors(MainYellow, Black, Gray, Black)) {
                    Text(stringResource(id = R.string.login_login_button))
               }

               //TODO this button has to be a more elaborated component to switch between customer and company
               Button(onClick = { onRegisterClicked() }, colors = ButtonDefaults.buttonColors(Transparent,Black, Gray,Black)) {
                   Text(if(isCompany){stringResource(id = R.string.login_no_account_company)}else{stringResource(id = R.string.login_no_account_customer)})

               }
           }

           Button(onClick = { isCompany = !isCompany} , modifier = Modifier.align(Alignment.BottomEnd), colors = ButtonDefaults.buttonColors(
               Transparent, Black,  Gray, Black)) {
               Text(if(isCompany){stringResource(id = R.string.login_customer_access)}else{stringResource(id = R.string.login_company_access)})

           }



       }
   }
}

@Preview
@Composable
fun LoginScreen_Preview() {
    LoginScreenContent( {}) {_,_ ->}
}


//-----Form Field


@Composable
fun FormField(text: String,
              leadingIcon: ImageVector,
              trailingIcon:ImageVector? = null,
              isPassword: Boolean = false,
              onValueChange: (String) -> (Unit),
              label: (@Composable () -> (Unit))? = null) {

    FormFieldContent(text, leadingIcon, trailingIcon,isPassword,onValueChange,label)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormFieldContent(text: String,
                     leadingIcon: ImageVector,
                     trailingIcon:ImageVector? = null,
                     isPassword: Boolean = false,
                     onValueChange: (String) -> (Unit),
                     label: (@Composable () -> (Unit))? = null)
{
    TextField(value = text,
    onValueChange = onValueChange,
    leadingIcon = {
        Icon(imageVector = leadingIcon, contentDescription = leadingIcon.name)
    },
        trailingIcon = {
            trailingIcon?.let {
                Icon(imageVector = it, contentDescription  = trailingIcon.name)
            }
        },
        placeholder = {
            if (label != null){
                label()
            }
        }
    )
}

@Preview
@Composable
fun FormField_Preview() {
    FormFieldContent("", Icons.Default.Lock, Icons.Default.Warning, true , {_->Unit}, {
        FormLabel(
        hint = "Password", TextDecoration.None
    )
    })
}

//----- FormLabel
@Composable
fun FormLabel(hint: String, textDecoration: TextDecoration = TextDecoration.None){
    Text(text = hint, textDecoration = textDecoration)
}
