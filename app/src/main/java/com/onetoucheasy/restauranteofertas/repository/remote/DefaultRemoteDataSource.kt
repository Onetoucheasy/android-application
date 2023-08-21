package com.onetoucheasy.restauranteofertas.repository.remote

import android.util.Log
import com.auth0.android.jwt.JWT
import com.onetoucheasy.restauranteofertas.repository.remote.request.SignUpRequestBody
import com.onetoucheasy.restauranteofertas.repository.remote.response.JWTResponse
import com.onetoucheasy.restauranteofertas.repository.remote.response.OffersResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultRemoteDataSource @Inject constructor(
    private val api: OneTouchApi
) : RemoteDataSource{
    private lateinit var tokenData: JWTResponse
    private val apiKey: String = "uciEfKBulHfMpavLobwGaIE0XwZBaCqBQuSRQu4YaZiNWGyON1XNlG7djMJ9Ogt5"

    override suspend fun performLogin(loginData: String): JWTResponse? {
        return try {
            this.tokenData = api.performLogin(this.apiKey, loginData)
            this.tokenData
        } catch (exception: Exception) {
            Log.d("Exception Login", "${exception.message}")
            throw exception
        }
    }
    override suspend fun performSignUp(signUpRequestBody: SignUpRequestBody): JWTResponse? {
        return try {
            this.tokenData = api.performSignUp(this.apiKey, signUpRequestBody)
            this.tokenData
        } catch (exception: Exception) {
            Log.d("Exception SignUP", "${exception.message}")
            throw exception
        }
    }

    override suspend fun getOffers(): OffersResponse {
        return try {
            api.getOffers(this.apiKey)
        }catch (exception: Exception){
            Log.d("Exception get offers", "${exception.message}")
            throw exception
        }
    }
}