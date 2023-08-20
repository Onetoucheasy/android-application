package com.onetoucheasy.restauranteofertas.repository.remote

import com.onetoucheasy.restauranteofertas.repository.remote.request.SignUpRequestBody
import com.onetoucheasy.restauranteofertas.repository.remote.response.JWTResponse

interface RemoteDataSource {
    suspend fun performLogin(loginData: String): JWTResponse?

    suspend fun performSignUp(signUpRequestBody: SignUpRequestBody): JWTResponse?
}