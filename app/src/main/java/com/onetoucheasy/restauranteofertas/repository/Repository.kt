package com.onetoucheasy.restauranteofertas.repository

interface Repository {
    suspend fun performLogin(loginData: String): String
}