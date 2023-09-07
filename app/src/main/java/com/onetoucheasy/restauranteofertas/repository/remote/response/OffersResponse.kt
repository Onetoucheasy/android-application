package com.onetoucheasy.restauranteofertas.repository.remote.response

import com.onetoucheasy.restauranteofertas.repository.local.model.LocalOffer
import com.squareup.moshi.Json

data class OffersResponse(
    @Json(name = "items") val totalResults: Int?, // for mock api, use "totalResults"
    @Json(name = "result") val restaurants: List<Restaurant> // for mock api, use "restaurants"
)

data class Restaurant(
    @Json(name = "id") val id: String,
    @Json(name = "picture") val picture: String, // new
    @Json(name = "name") val name: String,
    @Json(name = "type") val type: String = "",
//    @Json(name = "latitude") val latitude: String?, // esta en otro lugar
//    @Json(name = "longitude") val longitude: String?, // esta en otro lugar
//    @Json(name = "openingHour") val openingHour: String?, // esta en otro lugar
//    @Json(name = "closingHour") val closingHour: String?, // esta en otro lugar
    @Json(name = "offers") val offers: List<Offers> // try List<LocalOffer>
)

data class Offers(
    @Json(name = "id") val id: String,
    @Json(name = "title") val offerName: String, // for mock api, use "offerName"
    @Json(name = "description") val description: String,
    @Json(name = "image") val image: String,
    @Json(name = "startHour") val startTime: String, // for mock api, use "startTime"
    @Json(name = "endHour") val endTime: String,  // for mock api, use "endTime"
    @Json(name = "createdDate") val postTime: String // not used, for mock api, use "postTime"
)
