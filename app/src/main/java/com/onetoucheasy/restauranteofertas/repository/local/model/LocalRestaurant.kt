package com.onetoucheasy.restauranteofertas.repository.local.model

import com.onetoucheasy.restauranteofertas.repository.remote.response.Offers

data class LocalRestaurant(
    val id: String,
    val name: String,
    val type: String,
//    val latitude: String,  // for mock api, use this field
//    val longitude: String,  // for mock api, use this field
//    val openingHour: String,  // for mock api, use this field
//    val closingHour: String,  // for mock api, use this field
    val offers: List<Offers> // try List<LocalOffer>
)