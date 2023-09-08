package com.onetoucheasy.restauranteofertas.repository.remote

import com.onetoucheasy.restauranteofertas.repository.remote.request.SignUpRequestBody
import com.onetoucheasy.restauranteofertas.repository.remote.response.JWTResponse
import com.onetoucheasy.restauranteofertas.repository.remote.response.Offers
import com.onetoucheasy.restauranteofertas.repository.remote.response.OffersResponse

interface RemoteDataSource {
    suspend fun performLogin(loginData: String): JWTResponse?
    suspend fun performSignUp(signUpRequestBody: SignUpRequestBody): JWTResponse?
    suspend fun getOffers(): OffersResponse
    suspend fun getOfferById(offerId: String): Offers
    suspend fun getRestaurants(): OffersResponse
}