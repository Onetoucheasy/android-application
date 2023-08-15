package com.onetoucheasy.restauranteofertas.repository.remote

import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

//TODO Refactor login method
interface OneTouchApi {

    @GET("auth/signIn")
    suspend fun performLogin(@Header("CDS-ApiKey") apiKey: String, @Header("Authorization") loginData: String): String
}