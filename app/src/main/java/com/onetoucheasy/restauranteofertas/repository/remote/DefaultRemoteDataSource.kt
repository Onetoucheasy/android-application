package com.onetoucheasy.restauranteofertas.repository.remote

import android.util.Log
import com.onetoucheasy.restauranteofertas.repository.remote.request.SignUpRequestBody
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultRemoteDataSource @Inject constructor(
    private val api: OneTouchApi
) : RemoteDataSource{
    private var token = ""
    private val apiKey: String = "uciEfKBulHfMpavLobwGaIE0XwZBaCqBQuSRQu4YaZiNWGyON1XNlG7djMJ9Ogt5"

    override suspend fun performLogin(loginData: String): Boolean {
        return try {
            this.token = api.performLogin(this.apiKey, loginData)
            token.isNotEmpty()
        } catch (exception: Exception) {
            Log.d("Exception Login", "${exception.message}")
            false
        }
    }
    override suspend fun performSignUp(signUpRequestBody: SignUpRequestBody): String {
        return try {
            this.token = api.performSignUp(this.apiKey, signUpRequestBody)
            return this.token
        } catch (exception: Exception) {
            Log.d("Exception SignUP", "${exception.message}")
            throw exception
        }

    }
}
