package com.onetoucheasy.restauranteofertas.repository.remote

import retrofit2.http.Header
import retrofit2.http.POST

//TODO: Refactor DragonBallAPI name (files in which this file appears: RemoteModule,
//TODO Refactor login method
interface DragonBallApi {
    //Login
    @POST("/api/auth/login")
    suspend fun performLogin(@Header("Authorization") loginData: String): String
}