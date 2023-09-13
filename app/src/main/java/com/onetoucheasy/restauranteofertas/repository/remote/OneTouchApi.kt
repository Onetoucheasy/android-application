package com.onetoucheasy.restauranteofertas.repository.remote

import com.onetoucheasy.restauranteofertas.repository.remote.request.SignUpRequestBody
import com.onetoucheasy.restauranteofertas.repository.remote.response.JWTResponse
import com.onetoucheasy.restauranteofertas.repository.remote.response.Offer
import com.onetoucheasy.restauranteofertas.repository.remote.response.OffersResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface OneTouchApi {

    @GET("auth/signIn")
    suspend fun performLogin(
        @Header("CDS-ApiKey") apiKey: String,
        @Header("Authorization") loginData: String
    ): JWTResponse

    @POST("auth/signUp")
    suspend fun performSignUp(
        @Header("CDS-ApiKey") apiKey: String,
        @Body signUpRequestBody: SignUpRequestBody
    ): JWTResponse

    @GET("offers")
    suspend fun getOffers(
        @Header("CDS-ApiKey") apiKey: String,
        @Header("Authorization") authHeader: String
    ): OffersResponse

    @GET("restaurantsWithOffer") // use "restaurants" for mock api
    suspend fun getRestaurants(
        @Header("CDS-ApiKey") apiKey: String,
        @Header("Authorization") authHeader: String
    ): OffersResponse

    @GET("offers/{offerId}") // add <offerID> used to fetch offer for DetailScreen
    suspend fun getOffersById(
        @Header("CDS-ApiKey") apiKey: String,
        @Header("Authorization") authHeader: String,
//        @Query("id") offerId: String // leads to [array] type response w multiple offers
        @Path("offerId") offerId: String
    ): Offer // return type of Array<Offer> if using @Query method
}