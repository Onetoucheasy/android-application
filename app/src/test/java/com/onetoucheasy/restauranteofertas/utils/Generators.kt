package com.onetoucheasy.restauranteofertas.utils

import com.onetoucheasy.restauranteofertas.repository.local.model.LocalOffer
import com.onetoucheasy.restauranteofertas.repository.local.model.LocalRestaurant
import com.onetoucheasy.restauranteofertas.repository.local.model.LocalRestaurantShortInfo
import com.onetoucheasy.restauranteofertas.repository.remote.response.Offers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


fun generateUIOffers(): Flow<List<LocalOffer>> {
    return flow {
        emit(listOf(LocalOffer("1", LocalRestaurantShortInfo("1", "Restaurant Pepe"), "2x1", "In all dishes", "", "19:00","20:30", "14:00")))
    }
}


fun generateUIRestaurants():  Flow<List<LocalRestaurant>>{
    return flow {
        emit(listOf(LocalRestaurant("1","Rest Pepe", "", "", "","","", listOf(Offers("", "", "","","","","")))))
    }
}

