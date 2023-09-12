package com.onetoucheasy.restauranteofertas.repository.remote.response

import com.squareup.moshi.Json

data class JWTResponse(
    @Json(name = "accessToken") val accessToken: String,
    @Json(name = "refreshToken") val refreshToken: String
)
