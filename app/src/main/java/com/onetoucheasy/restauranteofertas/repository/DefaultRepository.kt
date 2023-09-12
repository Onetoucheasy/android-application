package com.onetoucheasy.restauranteofertas.repository

import com.auth0.android.jwt.JWT
import com.onetoucheasy.restauranteofertas.repository.local.model.LocalOffer
import com.onetoucheasy.restauranteofertas.repository.local.model.LocalRestaurant
import com.onetoucheasy.restauranteofertas.repository.mappers.RemoteToLocalMapper
import com.onetoucheasy.restauranteofertas.repository.remote.response.JWTResponse
import com.onetoucheasy.restauranteofertas.repository.remote.RemoteDataSource
import com.onetoucheasy.restauranteofertas.repository.remote.request.SignUpRequestBody
import com.onetoucheasy.restauranteofertas.repository.remote.response.Offer
import kotlinx.coroutines.flow.Flow
import okhttp3.Credentials
import java.nio.charset.Charset
import javax.inject.Inject

class DefaultRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val remoteToLocalMapper: RemoteToLocalMapper,
): Repository {
    override suspend fun performLogin(loginData: String): JWTResponse? {
        return remoteDataSource.performLogin(loginData)
    }
    override suspend fun performSignUp(signUpRequestBody: SignUpRequestBody): JWTResponse? {
        return remoteDataSource.performSignUp(signUpRequestBody)
    }

    override suspend fun getOfferList(): Flow<List<LocalOffer>> {
        return remoteToLocalMapper.mapRestaurantsResponseToLocalOffers(remoteDataSource.getOffers())
    }

    override suspend fun getOfferById(offerId: String): Offer {
        return remoteDataSource.getOfferById(offerId)
    }

    override suspend fun getRestaurantList(): Flow<List<LocalRestaurant>> {
        return remoteToLocalMapper.mapRestaurantsResponseToLocalRestaurantsSingleList(remoteDataSource.getRestaurants())
    }
}