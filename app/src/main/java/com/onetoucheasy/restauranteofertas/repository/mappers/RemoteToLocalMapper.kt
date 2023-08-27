package com.onetoucheasy.restauranteofertas.repository.mappers

import com.onetoucheasy.restauranteofertas.repository.local.model.LocalOffer
import com.onetoucheasy.restauranteofertas.repository.local.model.LocalRestaurant
import com.onetoucheasy.restauranteofertas.repository.local.model.LocalRestaurantShortInfo
import com.onetoucheasy.restauranteofertas.repository.remote.response.Offers
import com.onetoucheasy.restauranteofertas.repository.remote.response.OffersResponse
import com.onetoucheasy.restauranteofertas.repository.remote.response.Restaurant
import javax.inject.Inject

class RemoteToLocalMapper @Inject constructor(){

    fun mapRestaurantsResponseToLocalOffers(getOfferResponse: OffersResponse): List<LocalOffer> {
        var localOffers =  getOfferResponse.restaurants.map { mapGetRestaurantResponseToLocalOfferList(it) }
        return localOffers.flatten()
    }

    fun mapRestaurantsResponseToLocalRestaurants(getRestaurantResponse: OffersResponse): List<LocalRestaurant> {
        var localOffers =  getRestaurantResponse.restaurants.map { mapGetRestaurantResponseToLocalRestaurantList(it) }
        return localOffers
    }

    private fun mapGetRestaurantResponseToLocalOfferList(restaurant: Restaurant): List<LocalOffer> {
        return restaurant.offers.map { mapGetOffers(it, restaurant)}
    }

    private fun mapGetRestaurantResponseToLocalRestaurantList(restaurant: Restaurant): LocalRestaurant {
        return LocalRestaurant(
            restaurant.id,
            restaurant.name,
            restaurant.type ,
            restaurant.latitude,
            restaurant.longitude,
            restaurant.openingHour,
            restaurant.closingHour,
            restaurant.offers
        )
    }

    private fun mapGetOffers(offer: Offers, restaurant: Restaurant): LocalOffer{
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
}