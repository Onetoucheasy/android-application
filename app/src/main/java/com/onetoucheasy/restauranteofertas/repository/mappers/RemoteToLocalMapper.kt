package com.onetoucheasy.restauranteofertas.repository.mappers

import com.onetoucheasy.restauranteofertas.repository.local.model.LocalOffer
import com.onetoucheasy.restauranteofertas.repository.local.model.LocalRestaurant
import com.onetoucheasy.restauranteofertas.repository.local.model.LocalRestaurantShortInfo
import com.onetoucheasy.restauranteofertas.repository.remote.response.Offer
import com.onetoucheasy.restauranteofertas.repository.remote.response.OffersResponse
import com.onetoucheasy.restauranteofertas.repository.remote.response.Restaurant
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class RemoteToLocalMapper @Inject constructor(){

    fun mapRestaurantsResponseToLocalOffers(getOfferResponse: OffersResponse): Flow<List<LocalOffer>> {
        val localOffers =  getOfferResponse.restaurants.map { mapGetRestaurantResponseToLocalOfferList(it) }
        return localOffers.asFlow()
    }
    private fun mapGetRestaurantResponseToLocalOfferList(restaurant: Restaurant): List<LocalOffer> {
        return restaurant.offers.map { mapGetOffers(it, restaurant)}
    }

    private fun mapGetRestaurantResponseToLocalRestaurantList(restaurant: Restaurant): List<LocalRestaurant> {
        return listOf(LocalRestaurant(
            restaurant.id,
            restaurant.name,
            restaurant.type ,
            restaurant.picture,
//            restaurant.latitude, // comment these out as they are not present in newer model
//            restaurant.longitude,
//            restaurant.openingHour,
//            restaurant.closingHour,
            restaurant.offers
        ))
    }

    private fun mapGetOffers(offer: Offer, restaurant: Restaurant): LocalOffer{ // try offer: LocalOffer??
        return LocalOffer(
            offer.id,
            LocalRestaurantShortInfo(restaurant.id, restaurant.name),
            offer.offerName,
            offer.description,
            offer.image,
            offer.startTime,
            offer.endTime,
            offer.postTime)
    }

    fun mapRestaurantsResponseToLocalRestaurantsSingleList(getRestaurantResponse: OffersResponse): Flow<List<LocalRestaurant>> {
        val localRestaurants = getRestaurantResponse.restaurants.flatMap { mapGetRestaurantResponseToLocalRestaurantList(it) }
        return flowOf(localRestaurants)
    }

}