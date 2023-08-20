package com.onetoucheasy.restauranteofertas.repository

import com.onetoucheasy.restauranteofertas.repository.remote.response.JWTResponse
import com.onetoucheasy.restauranteofertas.repository.remote.request.SignUpRequestBody

interface Repository {
    suspend fun performLogin(loginData: String): JWTResponse?
    suspend fun performSignUp(signUpRequestBody: SignUpRequestBody): JWTResponse?
}