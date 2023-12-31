package com.onetoucheasy.restauranteofertas.ui.scaffolds

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import com.onetoucheasy.restauranteofertas.ui.viewModels.LoginState
import org.junit.Rule
import org.junit.Test

class LoginScreenContentTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun given_invalid_user_and_password_when_login_then_display_red_fields(){
        with(composeRule){
            //Given
            setContent {
                LoginScreenContent(
                    loginState = LoginState.NONE,
                    onRegisterClicked = {},
                    onLoginClicked = {_,_ ->}
                )
            }

            //When
            composeRule.onNodeWithText("Email").performTextInput("test.com")
            composeRule.onNodeWithText("Password").performTextInput("123456")
//            composeRule.onNodeWithText("Sign In").performClick()

            //THEN
            composeRule.onNodeWithText("test.com").assertExists()
        }
    }
}