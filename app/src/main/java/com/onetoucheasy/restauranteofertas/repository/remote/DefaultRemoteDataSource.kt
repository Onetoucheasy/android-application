package com.onetoucheasy.restauranteofertas.repository.remote

import android.util.Log
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultRemoteDataSource @Inject constructor(
    private val api: DragonBallApi
) : RemoteDataSource{
    private var token = ""

    override suspend fun performLogin(loginData: String): String {
        this.token = api.performLogin(loginData)
        Log.d("token", token)
        return token //TODO If the token stay in the REpository, it should not be sent to the repository and so on
    }
}