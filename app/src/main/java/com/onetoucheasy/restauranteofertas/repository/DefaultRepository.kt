package com.onetoucheasy.restauranteofertas.repository

import com.onetoucheasy.restauranteofertas.repository.remote.response.JWTResponse
import com.onetoucheasy.restauranteofertas.repository.remote.RemoteDataSource
import com.onetoucheasy.restauranteofertas.repository.remote.request.SignUpRequestBody
import javax.inject.Inject

class DefaultRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
): Repository {
    override suspend fun performLogin(loginData: String): JWTResponse? {
        return remoteDataSource.performLogin(loginData)
    }

    override suspend fun performSignUp(signUpRequestBody: SignUpRequestBody): JWTResponse? {
        return remoteDataSource.performSignUp(signUpRequestBody)
    }
}