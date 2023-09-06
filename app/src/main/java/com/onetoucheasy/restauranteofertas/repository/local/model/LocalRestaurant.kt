package com.onetoucheasy.restauranteofertas.repository.local.model

import com.onetoucheasy.restauranteofertas.repository.remote.response.Offers

data class LocalRestaurant(
    val id: String,
    val name: String,
    val type: String,
    val latitude: String,
    val longitude: String,
    val openingHour: String,
    val closingHour: String,
    val offers: List<Offers> // try List<LocalOffer>
)