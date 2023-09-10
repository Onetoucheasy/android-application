package com.onetoucheasy.restauranteofertas.data.remote.fakes

import com.onetoucheasy.restauranteofertas.repository.remote.RemoteDataSource
import com.onetoucheasy.restauranteofertas.repository.remote.request.SignUpRequestBody
import com.onetoucheasy.restauranteofertas.repository.remote.response.JWTResponse
import com.onetoucheasy.restauranteofertas.repository.remote.response.Offer
import com.onetoucheasy.restauranteofertas.repository.remote.response.OffersResponse

class FakeRemoteDataSource: RemoteDataSource {

    override suspend fun performLogin(loginData: String): JWTResponse? {
        TODO("Not yet implemented")
    }

    override suspend fun performSignUp(signUpRequestBody: SignUpRequestBody): JWTResponse? {
        TODO("Not yet implemented")
    }

    override suspend fun getOfferById(offerId: String): Offer {
        TODO("Not yet implemented")
    }
    override suspend fun getOffers(): OffersResponse {
        TODO("Not yet implemented")
    }

    override suspend fun getRestaurants(): OffersResponse {
        TODO("Not yet implemented")
    }
}