package com.onetoucheasy.restauranteofertas.repository

import com.onetoucheasy.restauranteofertas.repository.remote.request.SignUpRequestBody

interface Repository {
    suspend fun performLogin(loginData: String): Boolean
    suspend fun performSignUp(signUpRequestBody: SignUpRequestBody): String
}