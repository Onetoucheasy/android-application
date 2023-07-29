package com.onetoucheasy.restauranteofertas.repository.remote

interface RemoteDataSource {
    suspend fun performLogin(loginData: String): Boolean
}