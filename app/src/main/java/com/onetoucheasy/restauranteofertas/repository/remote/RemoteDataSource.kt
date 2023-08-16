package com.onetoucheasy.restauranteofertas.repository.remote

import com.onetoucheasy.restauranteofertas.repository.remote.request.SignUpRequestBody

interface RemoteDataSource {
    suspend fun performLogin(loginData: String): Boolean

    suspend fun performSignUp(signUpRequestBody: SignUpRequestBody): String
}