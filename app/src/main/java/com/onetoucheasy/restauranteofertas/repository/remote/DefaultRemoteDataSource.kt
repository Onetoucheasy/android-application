package com.onetoucheasy.restauranteofertas.repository.remote

import android.util.Log
import com.onetoucheasy.restauranteofertas.repository.remote.request.SignUpRequestBody
import com.onetoucheasy.restauranteofertas.repository.remote.response.JWTResponse
import com.onetoucheasy.restauranteofertas.repository.remote.response.Offer
import com.onetoucheasy.restauranteofertas.repository.remote.response.OffersResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultRemoteDataSource @Inject constructor(
    private val api: OneTouchApi
) : RemoteDataSource{
    private lateinit var tokenData: JWTResponse
    private val apiKey: String = "uciEfKBulHfMpavLobwGaIE0XwZBaCqBQuSRQu4YaZiNWGyON1XNlG7djMJ9Ogt5"

    override suspend fun performLogin(loginData: String): JWTResponse? {
        return try {
            this.tokenData = api.performLogin(this.apiKey, loginData)
            this.tokenData
        } catch (exception: Exception) {
            Log.d("Exception Login", "${exception.message}")
            throw exception
        }
    }
    override suspend fun performSignUp(signUpRequestBody: SignUpRequestBody): JWTResponse? {
        return try {
            this.tokenData = api.performSignUp(this.apiKey, signUpRequestBody)
            this.tokenData
        } catch (exception: Exception) {
            Log.d("Exception SignUP", "${exception.message}")
            throw exception
        }
    }

    override suspend fun getOffers(): OffersResponse {
        return try {
            val authHeaderValue = "Bearer ${tokenData.accessToken}"
//            api.getOffersEndpoint2("https://us-central1-projectkc-6ca03.cloudfunctions.net/offers")
            api.getOffers(this.apiKey, authHeaderValue)
        }catch (exception: Exception){
            Log.d("Exception get offers", "${exception.message}")
            throw exception
        }
    }
    override suspend fun getOfferById(offerId: String): Offer {
        return try {
            val authHeaderValue = "Bearer ${tokenData.accessToken}"
            api.getOffersById(this.apiKey, authHeaderValue, offerId)
        }catch (exception: Exception){
            Log.d("Exception get restaurants", "${exception.message}")
            throw exception
        }
    } // ⚠️ Not complete. "Name"

    override suspend fun getRestaurants(): OffersResponse {
        return try {
            val authHeaderValue = "Bearer ${tokenData.accessToken}"
//            api.getOffersEndpoint2("https://us-central1-projectkc-6ca03.cloudfunctions.net/offers")
            api.getRestaurants(this.apiKey, authHeaderValue)
        }catch (exception: Exception){
            Log.d("Exception get restaurants", "${exception.message}")
            throw exception
        }
    }
}