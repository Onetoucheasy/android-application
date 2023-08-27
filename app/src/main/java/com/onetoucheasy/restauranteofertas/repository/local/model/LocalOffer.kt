package com.onetoucheasy.restauranteofertas.repository.local.model

data class LocalOffer(
    val id: String,
    val restaurant: LocalRestaurantShortInfo,
    val offerName: String,
    val description: String,
    val image: String,
    val startTime: String,
    val endTime: String,
    val postTime: String
)