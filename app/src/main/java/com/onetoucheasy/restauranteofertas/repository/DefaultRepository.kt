package com.onetoucheasy.restauranteofertas.repository

import com.onetoucheasy.restauranteofertas.repository.remote.RemoteDataSource
import javax.inject.Inject

class DefaultRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
): Repository {
    override suspend fun performLogin(loginData: String): String {
        return remoteDataSource.performLogin(loginData)
    }
}