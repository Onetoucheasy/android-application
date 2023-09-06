package com.onetoucheasy.restauranteofertas.repository.remote.response

import com.onetoucheasy.restauranteofertas.repository.local.model.LocalOffer
import com.squareup.moshi.Json

data class OffersResponse(
    @Json(name = "totalResults") val totalResults: Int,
    @Json(name = "restaurants") val restaurants: List<Restaurant>
)

data class Restaurant(
    @Json(name = "id") val id: String,
    @Json(name = "name") val name: String,
    @Json(name = "type") val type: String = "",
    @Json(name = "latitude") val latitude: String,
    @Json(name = "longitude") val longitude: String,
    @Json(name = "openingHour") val openingHour: String,
    @Json(name = "closingHour") val closingHour: String,
    @Json(name = "offers") val offers: List<Offers> // try List<LocalOffer>
)

data class Offers(
    @Json(name = "id") val id: String,
    @Json(name = "offerName") val offerName: String,
    @Json(name = "description") val description: String,
    @Json(name = "image") val image: String,
    @Json(name = "startTime") val startTime: String,
    @Json(name = "endTime") val endTime: String,
    @Json(name = "postTime") val postTime: String
)
