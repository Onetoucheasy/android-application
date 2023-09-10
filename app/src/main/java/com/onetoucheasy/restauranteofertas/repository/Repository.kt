package com.onetoucheasy.restauranteofertas.repository

import com.onetoucheasy.restauranteofertas.repository.local.model.LocalOffer
import com.onetoucheasy.restauranteofertas.repository.local.model.LocalRestaurant
import com.onetoucheasy.restauranteofertas.repository.remote.request.SignUpRequestBody
import com.onetoucheasy.restauranteofertas.repository.remote.response.JWTResponse
import com.onetoucheasy.restauranteofertas.repository.remote.response.Offer
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun performLogin(loginData: String): JWTResponse?
    suspend fun performSignUp(signUpRequestBody: SignUpRequestBody): JWTResponse?
    //suspend fun getOffers(): OffersResponse
    suspend fun getOfferList(): Flow<List<LocalOffer>>
    suspend fun getOfferById(offerId: String): Offer
    suspend fun getRestaurantList(): Flow<List<LocalRestaurant>>
}