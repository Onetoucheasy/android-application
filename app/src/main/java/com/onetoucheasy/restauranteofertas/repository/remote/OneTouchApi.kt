package com.onetoucheasy.restauranteofertas.repository.remote

import com.onetoucheasy.restauranteofertas.repository.remote.request.SignUpRequestBody
import com.onetoucheasy.restauranteofertas.repository.remote.response.JWTResponse
import com.onetoucheasy.restauranteofertas.repository.remote.response.OffersResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface OneTouchApi {

    @GET("auth/signIn")
    suspend fun performLogin(
        @Header("CDS-ApiKey") apiKey: String,
        @Header("Authorization") loginData: String
    ): JWTResponse

    @POST("auth/signUp")
    suspend fun performSignUp(
        @Header("CDS-ApiKey") apiKey: String,
        @Body signUpRequestBody: SignUpRequestBody
    ): JWTResponse

    @GET("offers")
    suspend fun getOffers(
        @Header("CDS-ApiKey") apiKey: String
    ): OffersResponse

    @GET("restaurants")
    suspend fun getRestaurants(
        @Header("CDS-ApiKey") apiKey: String
    ): OffersResponse
}