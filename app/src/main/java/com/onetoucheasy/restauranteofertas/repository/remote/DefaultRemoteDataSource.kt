package com.onetoucheasy.restauranteofertas.repository.remote

import android.util.Log
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultRemoteDataSource @Inject constructor(
    private val api: DragonBallApi
) : RemoteDataSource{
    private var token = ""

    override suspend fun performLogin(loginData: String): Boolean {
        this.token = api.performLogin(loginData)
        return token.isNotEmpty()
    }
}