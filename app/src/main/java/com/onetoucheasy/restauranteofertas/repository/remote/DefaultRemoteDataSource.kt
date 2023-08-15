package com.onetoucheasy.restauranteofertas.repository.remote

import android.util.Log
import com.auth0.android.jwt.JWT
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
            this.token = api.performLogin(this.apiKey,loginData)
            token.isNotEmpty()
        }catch (exception: Exception){
            Log.d("Exception Login", "${exception.message}")
            false
        }
    }
}