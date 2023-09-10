package com.onetoucheasy.restauranteofertas.utils

import com.onetoucheasy.restauranteofertas.repository.local.model.LocalOffer
import com.onetoucheasy.restauranteofertas.repository.local.model.LocalRestaurant
import com.onetoucheasy.restauranteofertas.repository.local.model.LocalRestaurantShortInfo
import com.onetoucheasy.restauranteofertas.repository.remote.response.Offers
import com.onetoucheasy.restauranteofertas.ui.scaffolds.offerMock3
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

fun generateUIOffers(): Flow<List<LocalOffer>> {
    return flow {
        emit(listOf(LocalOffer("1", LocalRestaurantShortInfo("1", "Restaurant Pepe"), "2x1", "In all dishes", "", "19:00","20:30", "14:00")))
    }
}

fun generateOfferById(offerId: String): Offers {
    return offerMock3
}

fun generateUIRestaurants():  Flow<List<LocalRestaurant>>{
    return flow {
        emit(listOf(LocalRestaurant(
            id = "789",
            name = "Restaurante Mock 3",
            type = "Shushi Mock",
            picture = "https://images.pexels.com/photos/18078297/pexels-photo-18078297/free-photo-of-ciudad-calle-barra-urbano.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
            offers = listOf(
                Offers(
                    id = "b2e21a5e-958f-4ab8-84fe-7d78b63b9301",
                    offerName = "OfferNameMock3-1",
                    description = "Mock description3-1 mock Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, ",
                    image = "https://www.camarero10.com/wp-content/uploads/2020/02/como-distribuir-un-restaurante.jpg",
                    startTime = "2023-08-09T15:00:00Z",
                    endTime = "2023-08-09T17:00:00Z",
                    postTime = "22023-08-09T11:00:00Z"
                ),
                Offers(
                    id = "b2e21a5e-958f-4ab8-84fe-7d78b63b9302",
                    offerName = "OfferNameMock3-2",
                    description = "Mock description3-2 mock Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, ",
                    image = "https://www.camarero10.com/wp-content/uploads/2020/02/como-distribuir-un-restaurante.jpg",
                    startTime = "2023-08-09T15:00:00Z",
                    endTime = "2023-08-09T17:00:00Z",
                    postTime = "22023-08-09T11:00:00Z"
                ),
                Offers(
                    id = "b2e21a5e-958f-4ab8-84fe-7d78b63b9303",
                    offerName = "OfferNameMock3-3",
                    description = "Mock description3-3 mock Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, ",
                    image = "https://www.camarero10.com/wp-content/uploads/2020/02/como-distribuir-un-restaurante.jpg",
                    startTime = "2023-08-09T15:00:00Z",
                    endTime = "2023-08-09T17:00:00Z",
                    postTime = "22023-08-09T11:00:00Z"
                )
            )
        )))
    }
}

