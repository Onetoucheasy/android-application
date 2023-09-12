package com.onetoucheasy.restauranteofertas.repository

import com.onetoucheasy.restauranteofertas.repository.local.model.LocalOffer
import com.onetoucheasy.restauranteofertas.repository.local.model.LocalRestaurant
import com.onetoucheasy.restauranteofertas.repository.local.model.LocalRestaurantShortInfo
import com.onetoucheasy.restauranteofertas.repository.remote.response.Offer
import com.onetoucheasy.restauranteofertas.repository.remote.response.Restaurant

var offerMock11 = Offer(
    id = "b2e21a5e-958f-4ab8-84fe-7d78b63b9101",
    offerName = "OfferNameMock1-1",
    description = "Mock description1-1 mock Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, ",
    image = "https://images.pexels.com/photos/12046657/pexels-photo-12046657.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
    startTime = "2023-08-09T15:00:00Z",
    endTime = "2023-08-09T17:00:00Z",
    postTime = "22023-08-09T11:00:00Z"
)
var offerMock12 = Offer(
    id = "b2e21a5e-958f-4ab8-84fe-7d78b63b9102",
    offerName = "OfferNameMock1-2",
    description = "Mock description1-2 mock Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, ",
    image = "https://www.camarero10.com/wp-content/uploads/2020/02/como-distribuir-un-restaurante.jpg",
    startTime = "2023-08-09T15:00:00Z",
    endTime = "2023-08-09T17:00:00Z",
    postTime = "22023-08-09T11:00:00Z"
)
var offerMock13 = Offer(
    id = "b2e21a5e-958f-4ab8-84fe-7d78b63b9103",
    offerName = "OfferNameMock1-3",
    description = "Mock description1-3 mock Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, ",
    image = "https://www.camarero10.com/wp-content/uploads/2020/02/como-distribuir-un-restaurante.jpg",
    startTime = "2023-08-09T15:00:00Z",
    endTime = "2023-08-09T17:00:00Z",
    postTime = "22023-08-09T11:00:00Z"
)
var offerMock21 = Offer(
    id = "b2e21a5e-958f-4ab8-84fe-7d78b63b9201",
    offerName = "OfferNameMock2-1",
    description = "Mock description2-1 mock Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, ",
    image = "https://www.camarero10.com/wp-content/uploads/2020/02/como-distribuir-un-restaurante.jpg",
    startTime = "2023-08-09T15:00:00Z",
    endTime = "2023-08-09T17:00:00Z",
    postTime = "22023-08-09T11:00:00Z"
)
var offerMock22 = Offer(
    id = "b2e21a5e-958f-4ab8-84fe-7d78b63b9202",
    offerName = "OfferNameMock2-2",
    description = "Mock description2-2 mock Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, ",
    image = "https://www.camarero10.com/wp-content/uploads/2020/02/como-distribuir-un-restaurante.jpg",
    startTime = "2023-08-09T15:00:00Z",
    endTime = "2023-08-09T17:00:00Z",
    postTime = "22023-08-09T11:00:00Z"
)
var offerMock23 = Offer(
    id = "b2e21a5e-958f-4ab8-84fe-7d78b63b9203",
    offerName = "OfferNameMock2-3",
    description = "Mock description2-3 mock Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, ",
    image = "https://www.camarero10.com/wp-content/uploads/2020/02/como-distribuir-un-restaurante.jpg",
    startTime = "2023-08-09T15:00:00Z",
    endTime = "2023-08-09T17:00:00Z",
    postTime = "22023-08-09T11:00:00Z"
)
var offerMock31 = Offer(
    id = "b2e21a5e-958f-4ab8-84fe-7d78b63b9301",
    offerName = "OfferNameMock3-1",
    description = "Mock description3-1 mock Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, ",
    image = "https://www.camarero10.com/wp-content/uploads/2020/02/como-distribuir-un-restaurante.jpg",
    startTime = "2023-08-09T15:00:00Z",
    endTime = "2023-08-09T17:00:00Z",
    postTime = "22023-08-09T11:00:00Z"
)
var offerMock32 = Offer(
    id = "b2e21a5e-958f-4ab8-84fe-7d78b63b9302",
    offerName = "OfferNameMock3-2",
    description = "Mock description3-2 mock Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, ",
    image = "https://www.camarero10.com/wp-content/uploads/2020/02/como-distribuir-un-restaurante.jpg",
    startTime = "2023-08-09T15:00:00Z",
    endTime = "2023-08-09T17:00:00Z",
    postTime = "22023-08-09T11:00:00Z"
)
var offerMock33 = Offer(
    id = "b2e21a5e-958f-4ab8-84fe-7d78b63b9303",
    offerName = "OfferNameMock3-3",
    description = "Mock description3-3 mock Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, ",
    image = "https://www.camarero10.com/wp-content/uploads/2020/02/como-distribuir-un-restaurante.jpg",
    startTime = "2023-08-09T15:00:00Z",
    endTime = "2023-08-09T17:00:00Z",
    postTime = "22023-08-09T11:00:00Z"
)

var localRestaurantMock1 = LocalRestaurant(
    id = "b2e21a5e-958f-4ab8-84fe-7d78b63b9100",
    name = "Restaurante Mock 1",
    type = "Tapas Mock",
    picture = "https://images.pexels.com/photos/18078297/pexels-photo-18078297/free-photo-of-ciudad-calle-barra-urbano.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
    offers = listOf<Offer>(offerMock11, offerMock12, offerMock13)
)
var localRestaurantMock2 = LocalRestaurant(
    id = "b2e21a5e-958f-4ab8-84fe-7d78b63b9200",
    name = "Restaurante Mock 2",
    type = "Shushi Mock",
    picture = "https://images.pexels.com/photos/18078297/pexels-photo-18078297/free-photo-of-ciudad-calle-barra-urbano.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
    offers = listOf<Offer>(offerMock21, offerMock22, offerMock23)
)
var localRestaurantMock3 = LocalRestaurant(
    id = "b2e21a5e-958f-4ab8-84fe-7d78b63b9300",
    name = "Restaurante Mock 3",
    type = "Shushi Mock",
    picture = "https://images.pexels.com/photos/18078297/pexels-photo-18078297/free-photo-of-ciudad-calle-barra-urbano.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
    offers = listOf<Offer>(offerMock31, offerMock32, offerMock33)
)

var localOfferMock1 = LocalOffer("1",
    restaurant = LocalRestaurantShortInfo(name = "Restaurant Name",
        id = "String"),
    "2x1 en Carta",
    "2x1 en toda la carta, excepto postres y bebidas.",
    "",
    "14:30",
    "17:30",
    ""
)
var localOfferMock2 = LocalOffer("2",
    restaurant = LocalRestaurantShortInfo(name = "Restaurant Name",
        id = "String"),
    "3x1 en Carta",
    "3x1 en toda la carta, excepto postres y bebidas.",
    "",
    "19:30",
    "20:30",
    ""
)
var restaurant1 = Restaurant("1",
"",
"Mao Sushi",
"",
listOf(Offer("1", "2x1","","","","","")))

var restaurant2 = Restaurant("2",
    "",
    "Pizzeria di Carlo",
    "",
    listOf(Offer("2", "3x2","3x2 de 22:00 a 23:00","","22:00","23:00","")))

var localRestaurantListMock = listOf<LocalRestaurant>(localRestaurantMock1, localRestaurantMock2, localRestaurantMock3)
var offerListMock = listOf<Offer>(offerMock11, offerMock12)
var offerMutableListMock = mutableListOf<Offer>()
var localRestaurantMutableListMock = mutableListOf<LocalRestaurant>()
