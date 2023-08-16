package com.onetoucheasy.restauranteofertas.repository.remote

import com.onetoucheasy.restauranteofertas.repository.remote.request.SignUpRequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

//TODO Refactor login method
interface OneTouchApi {
    @GET("auth/signIn")
    suspend fun performLogin(
        @Header("CDS-ApiKey") apiKey: String,
        @Header("Authorization") loginData: String
    ): String

    @POST("auth/signUp")
    suspend fun performSignUp(
        @Header("CDS-ApiKey") apiKey: String,
        @Body signUpRequestBody: SignUpRequestBody
    ): String
}