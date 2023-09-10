package com.onetoucheasy.restauranteofertas.repository

import com.onetoucheasy.restauranteofertas.repository.local.model.LocalOffer
import com.onetoucheasy.restauranteofertas.repository.local.model.LocalRestaurant
import com.onetoucheasy.restauranteofertas.repository.local.model.LocalRestaurantShortInfo
import com.onetoucheasy.restauranteofertas.repository.remote.response.Offer

var offerMock1 = LocalOffer("1",
    restaurant = LocalRestaurantShortInfo(name = "Restaurant Name",
        id = "String"),
    "2x1 en Carta",
    "2x1 en toda la carta, excepto postres y bebidas.",
    "",
    "14:30",
    "17:30",
    "")

var offerMock2 = LocalOffer("2",
    restaurant = LocalRestaurantShortInfo(name = "Restaurant Name",
        id = "String"),
    "3x1 en Carta",
    "3x1 en toda la carta, excepto postres y bebidas.",
    "",
    "19:30",
    "20:30",
    "")

var offerMock3 = Offer(
    "3",
    "3x1 en Carta",
    "3x1 en toda la carta, excepto postres y bebidas.",
    "",
    "14:30",
    "17:30",
    "")

var offerMock4 = Offer(
    "4",
    "40% descuento ahora!",
    "4x1 en toda la carta, excepto postres y bebidas. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
    "https://www.camarero10.com/wp-content/uploads/2020/02/como-distribuir-un-restaurante.jpg",
    "2023-08-09T13:00:00Z\"",
    "2023-08-09T15:00:00Z",
    "")

var restauranteMock1 = LocalRestaurant(
    id = "123",
    name = "Restaurante Mock 1",
    type = "Tapas Mock",
    picture = "https://images.pexels.com/photos/18078297/pexels-photo-18078297/free-photo-of-ciudad-calle-barra-urbano.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
//    latitude = "40.23",
//    longitude = "-4.56",
//    openingHour = "1000",
//    closingHour = "2359",
    offers = listOf(
//        LocalOffer(
        Offer(
            id = "b2e21a5e-958f-4ab8-84fe-7d78b63b9101",
//            restaurant = LocalRestaurantShortInfo(
//                id = "123",
//                name = "Restaurante Mock 1"
//            ),
            offerName = "OfferNameMock1-1",
            description = "Mock description1-1 mock Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, ",
            image = "https://www.camarero10.com/wp-content/uploads/2020/02/como-distribuir-un-restaurante.jpg",
            startTime = "2023-08-09T15:00:00Z",
            endTime = "2023-08-09T17:00:00Z",
            postTime = "22023-08-09T11:00:00Z"
        ),
//        LocalOffer(
        Offer(
            id = "b2e21a5e-958f-4ab8-84fe-7d78b63b9102",
//            restaurant = LocalRestaurantShortInfo(
//                id = "123",
//                name = "Restaurante Mock 1"
//            ),
            offerName = "OfferNameMock1-2",
            description = "Mock description1-2 mock Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, ",
            image = "https://www.camarero10.com/wp-content/uploads/2020/02/como-distribuir-un-restaurante.jpg",
            startTime = "2023-08-09T15:00:00Z",
            endTime = "2023-08-09T17:00:00Z",
            postTime = "22023-08-09T11:00:00Z"
        ),
//        LocalOffer(
        Offer(
            id = "b2e21a5e-958f-4ab8-84fe-7d78b63b9103",
//            restaurant = LocalRestaurantShortInfo(
//                id = "123",
//                name = "Restaurante Mock 1"
//            ),
            offerName = "OfferNameMock1-3",
            description = "Mock description1-3 mock Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, ",
            image = "https://www.camarero10.com/wp-content/uploads/2020/02/como-distribuir-un-restaurante.jpg",
            startTime = "2023-08-09T15:00:00Z",
            endTime = "2023-08-09T17:00:00Z",
            postTime = "22023-08-09T11:00:00Z"
        )
    )
)

var restauranteMock2 = LocalRestaurant(
    id = "456",
    name = "Restaurante Mock 2",
    type = "Shushi Mock",
    picture = "https://images.pexels.com/photos/18078297/pexels-photo-18078297/free-photo-of-ciudad-calle-barra-urbano.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
//    latitude = "40.23",
//    longitude = "-4.56",
//    openingHour = "1000",
//    closingHour = "2359",
    offers = listOf(
//        LocalOffer(
        Offer(
            id = "b2e21a5e-958f-4ab8-84fe-7d78b63b9201",
//            restaurant = LocalRestaurantShortInfo(
//                id = "456",
//                name = "Restaurante Mock 2"
//            ),
            offerName = "OfferNameMock2-1",
            description = "Mock description2-1 mock Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, ",
            image = "https://www.camarero10.com/wp-content/uploads/2020/02/como-distribuir-un-restaurante.jpg",
            startTime = "2023-08-09T15:00:00Z",
            endTime = "2023-08-09T17:00:00Z",
            postTime = "22023-08-09T11:00:00Z"
        ),
//        LocalOffer(
        Offer(
            id = "b2e21a5e-958f-4ab8-84fe-7d78b63b9202",
//            restaurant = LocalRestaurantShortInfo(
//                id = "456",
//                name = "Restaurante Mock 2"
//            ),
            offerName = "OfferNameMock2-2",
            description = "Mock description2-2 mock Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, ",
            image = "https://www.camarero10.com/wp-content/uploads/2020/02/como-distribuir-un-restaurante.jpg",
            startTime = "2023-08-09T15:00:00Z",
            endTime = "2023-08-09T17:00:00Z",
            postTime = "22023-08-09T11:00:00Z"
        ),
//        LocalOffer(
        Offer(
            id = "b2e21a5e-958f-4ab8-84fe-7d78b63b9203",
//            restaurant = LocalRestaurantShortInfo(
//                id = "456",
//                name = "Restaurante Mock 2"
//            ),
            offerName = "OfferNameMock2-3",
            description = "Mock description2-3 mock Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, ",
            image = "https://www.camarero10.com/wp-content/uploads/2020/02/como-distribuir-un-restaurante.jpg",
            startTime = "2023-08-09T15:00:00Z",
            endTime = "2023-08-09T17:00:00Z",
            postTime = "22023-08-09T11:00:00Z"
        )
    )
)

var restauranteMock4 = LocalRestaurant(
    id = "4",
    name = "Rest Mock 4",
    type = "Type 4",
    picture = "https://images.pexels.com/photos/3421920/pexels-photo-3421920.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
//    latitude = "40.04",
//    longitude = "-4.04",
//    openingHour = "1004",
//    closingHour = "2304",
    offers = listOf(
//        LocalOffer(
        Offer(
            id = "401",
//            restaurant = LocalRestaurantShortInfo(
//                id = "4",
//                name = "Rest Mock 4"
//            ),
            offerName = "OfferNameMock41",
            description = "Mock desc 41 mock",
            image = "https://www.camarero10.com/wp-content/uploads/2020/02/como-distribuir-un-restaurante.jpg",
            startTime = "1604",
            endTime = "1804",
            postTime = "1504"
        ),
//        LocalOffer(
        Offer(
            id = "402",
//            restaurant = LocalRestaurantShortInfo(
//                id = "4",
//                name = "Rest Mock 4"
//            ),
            offerName = "OfferNameMock42",
            description = "Mock desc 42 mock",
            image = "https://www.restaurantelua.com/wp-content/uploads/2020/11/01_slider_restaurante_movil.jpg",
            startTime = "1604",
            endTime = "1804",
            postTime = "1504"
        ),
//        LocalOffer(
        Offer(
            id = "403",
//            restaurant = LocalRestaurantShortInfo(
//                id = "4",
//                name = "Rest Mock 4"
//            ),
            offerName = "OfferNameMock43",
            description = "Mock desc 43 mock",
            image = "https://www.hotelaiguablava.com/media/restaurante/espacios/restaurante/01a-restaurante-hotel-aigua-blava.jpg",
            startTime = "1604",
            endTime = "1804",
            postTime = "1504"
        )
    )
)
var restauranteMock5 = LocalRestaurant(
    id = "5",
    name = "Rest Mock 5",
    type = "Type 5",
    picture = "https://images.pexels.com/photos/18078297/pexels-photo-18078297/free-photo-of-ciudad-calle-barra-urbano.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
//    latitude = "50.05",
//    longitude = "-5.05",
//    openingHour = "1005",
//    closingHour = "2305",
    offers = listOf(
//        LocalOffer(
        Offer(
            id = "501",
//            restaurant = LocalRestaurantShortInfo(
//                id = "5",
//                name = "Rest Mock 5"
//            ),
            offerName = "OfferNameMock51",
            description = "Mock desc 51 mock",
            image = "https://www.image51.jpg",
            startTime = "1605",
            endTime = "1805",
            postTime = "1505"
        ),
//        LocalOffer(
        Offer(
            id = "502",
//            restaurant = LocalRestaurantShortInfo(
//                id = "5",
//                name = "Rest Mock 5"
//            ),
            offerName = "OfferNameMock52",
            description = "Mock desc 52 mock",
            image = "https://www.image52.jpg",
            startTime = "1605",
            endTime = "1805",
            postTime = "1505"
        ),
//        LocalOffer(
        Offer(
            id = "503",
//            restaurant = LocalRestaurantShortInfo(
//                id = "5",
//                name = "Rest Mock 5"
//            ),
            offerName = "OfferNameMock53",
            description = "Mock desc 53 mock",
            image = "https://www.image53.jpg",
            startTime = "1605",
            endTime = "1805",
            postTime = "1505"
        )
    )
)
var restauranteMock6 = LocalRestaurant(
    id = "6",
    name = "Rest Mock 6",
    type = "Type 6",
    picture = "https://images.pexels.com/photos/18078297/pexels-photo-18078297/free-photo-of-ciudad-calle-barra-urbano.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
//    latitude = "60.06",
//    longitude = "-6.06",
//    openingHour = "1006",
//    closingHour = "2306",
    offers = listOf(
//        LocalOffer(
        Offer(
            id = "601",
//            restaurant = LocalRestaurantShortInfo(
//                id = "6",
//                name = "Rest Mock 6"
//            ),
            offerName = "OfferNameMock61",
            description = "Mock desc 61 mock",
            image = "https://www.image61.jpg",
            startTime = "1606",
            endTime = "1806",
            postTime = "1506"
        ),
//        LocalOffer(
        Offer(
            id = "602",
//            restaurant = LocalRestaurantShortInfo(
//                id = "6",
//                name = "Rest Mock 6"
//            ),
            offerName = "OfferNameMock62",
            description = "Mock desc 62 mock",
            image = "https://www.image62.jpg",
            startTime = "1606",
            endTime = "1806",
            postTime = "1506"
        ),
//        LocalOffer(
        Offer(
            id = "603",
//            restaurant = LocalRestaurantShortInfo(
//                id = "6",
//                name = "Rest Mock 6"
//            ),
            offerName = "OfferNameMock63",
            description = "Mock desc 63 mock",
            image = "https://www.image63.jpg",
            startTime = "1606",
            endTime = "1806",
            postTime = "1506"
        )
    )
)
var restauranteMock3 = LocalRestaurant(
    id = "789",
    name = "Restaurante Mock 3",
    type = "Shushi Mock",
    picture = "https://images.pexels.com/photos/18078297/pexels-photo-18078297/free-photo-of-ciudad-calle-barra-urbano.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
//    latitude = "40.23",
//    longitude = "-4.56",
//    openingHour = "1000",
//    closingHour = "2359",
    offers = listOf(
//        LocalOffer(
        Offer(
            id = "b2e21a5e-958f-4ab8-84fe-7d78b63b9301",
//            restaurant = LocalRestaurantShortInfo(
//                id = "789",
//                name = "Restaurante Mock 3"
//            ),
            offerName = "OfferNameMock3-1",
            description = "Mock description3-1 mock Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, ",
            image = "https://www.camarero10.com/wp-content/uploads/2020/02/como-distribuir-un-restaurante.jpg",
            startTime = "2023-08-09T15:00:00Z",
            endTime = "2023-08-09T17:00:00Z",
            postTime = "22023-08-09T11:00:00Z"
        ),
//        LocalOffer(
        Offer(
            id = "b2e21a5e-958f-4ab8-84fe-7d78b63b9302",
//            restaurant = LocalRestaurantShortInfo(
//                id = "789",
//                name = "Restaurante Mock 3"
//            ),
            offerName = "OfferNameMock3-2",
            description = "Mock description3-2 mock Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, ",
            image = "https://www.camarero10.com/wp-content/uploads/2020/02/como-distribuir-un-restaurante.jpg",
            startTime = "2023-08-09T15:00:00Z",
            endTime = "2023-08-09T17:00:00Z",
            postTime = "22023-08-09T11:00:00Z"
        ),
//        LocalOffer(
        Offer(
            id = "b2e21a5e-958f-4ab8-84fe-7d78b63b9303",
//            restaurant = LocalRestaurantShortInfo(
//                id = "789",
//                name = "Restaurante Mock 3"
//            ),
            offerName = "OfferNameMock3-3",
            description = "Mock description3-3 mock Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, ",
            image = "https://www.camarero10.com/wp-content/uploads/2020/02/como-distribuir-un-restaurante.jpg",
            startTime = "2023-08-09T15:00:00Z",
            endTime = "2023-08-09T17:00:00Z",
            postTime = "22023-08-09T11:00:00Z"
        )
    )
)

var localRestaurantListMock = listOf<LocalRestaurant>(restauranteMock4, restauranteMock5, restauranteMock6)
var offerListMock3to4 = listOf<Offer>(offerMock3, offerMock4)
var offerListMock = mutableListOf<Offer>()
var localRestaurantMutableListMock = mutableListOf<LocalRestaurant>()
