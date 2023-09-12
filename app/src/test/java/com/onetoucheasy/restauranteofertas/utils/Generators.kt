package com.onetoucheasy.restauranteofertas.utils

import com.auth0.android.jwt.Claim
import com.onetoucheasy.restauranteofertas.repository.local.model.LocalOffer
import com.onetoucheasy.restauranteofertas.repository.local.model.LocalRestaurant
import com.onetoucheasy.restauranteofertas.repository.local.model.LocalRestaurantShortInfo
import com.onetoucheasy.restauranteofertas.repository.offerMock11
import com.onetoucheasy.restauranteofertas.repository.remote.response.JWTResponse
import com.onetoucheasy.restauranteofertas.repository.remote.response.Offer
import com.onetoucheasy.restauranteofertas.repository.remote.response.OffersResponse
import com.onetoucheasy.restauranteofertas.repository.restaurant1
import com.onetoucheasy.restauranteofertas.repository.restaurant2
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

fun generateJWTResponse(): JWTResponse? {
    return JWTResponse("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2OTI2NTAwODMuMzQ3NjcyOSwiaXNzIjoiT25ldG91Y2hlYXN5LlJlc3RhdXJhbnRlT2ZlcnRhcyIsImlzQ29tcGFueSI6InRydWUiLCJ0eXBlIjoiYWNjZXNUb2tlbiIsInN1YiI6Ijg2NkEyNzYwLTJDQ0EtNEY4Qi04MUE5LUZBMDgyMjQwODhENyJ9.vZ2iZEc7gP75y4LT_gDp1tKeJz1aS4rIloUQzkT25dI",
        "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2OTMyNTMwODMuMzQ3NjcyOSwiaXNzIjoiT25ldG91Y2hlYXN5LlJlc3RhdXJhbnRlT2ZlcnRhcyIsImlzQ29tcGFueSI6InRydWUiLCJ0eXBlIjoicmVmcmVzaFRva2VuIiwic3ViIjoiODY2QTI3NjAtMkNDQS00RjhCLTgxQTktRkEwODIyNDA4OEQ3In0.2-W63oZ4EOq9CU550snjjyHKc3Ywt-JDHp4t8KYwhuk")
}

fun generateJWTResponseUser(): JWTResponse? {
    return JWTResponse("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2OTIyODIxNjAuMDIyMDc4LCJpc3MiOiJPbmV0b3VjaGVhc3kuUmVzdGF1cmFudGVPZmVydGFzIiwiaXNDb21wYW55IjoiZmFsc2UiLCJ0eXBlIjoiYWNjZXNUb2tlbiIsInN1YiI6IjRBMEYzRkQyLTJCN0QtNDgyNS04MTczLUEzMUJCRURGMUVEMyJ9.AbldqpiFQEu_tPAuyoIB-o1N7o90x4nW3CrNn5RkbiY",
        "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2OTI4ODUxNjAuMDIyMDc4LCJpc3MiOiJPbmV0b3VjaGVhc3kuUmVzdGF1cmFudGVPZmVydGFzIiwiaXNDb21wYW55IjoiZmFsc2UiLCJ0eXBlIjoicmVmcmVzaFRva2VuIiwic3ViIjoiNEEwRjNGRDItMkI3RC00ODI1LTgxNzMtQTMxQkJFREYxRUQzIn0.BCL4C1i3fhFWks4EhS9CHVsg_PgcnCQsQdVuf-HivcM")
}

fun generateBoolData(): Boolean?{
    return true
}

fun generateClaims(): String {
    return "true"
}





fun generateUIOffers(): Flow<List<LocalOffer>> {
    return flow {
        emit(listOf(LocalOffer("1", LocalRestaurantShortInfo("1", "Restaurant Pepe"), "2x1", "In all dishes", "", "19:00","20:30", "14:00")))
    }
}

fun generateOfferById(offerId: String): Offer {
    return offerMock11
}

fun generateOfferList(): OffersResponse {
    return OffersResponse(1, listOf(restaurant1))
}

fun generateRestaurantList(): OffersResponse {
    return OffersResponse(2, listOf(restaurant1, restaurant2))
}


fun generateUIRestaurants():  Flow<List<LocalRestaurant>>{
    return flow {
        emit(listOf(LocalRestaurant(
            id = "789",
            name = "Restaurante Mock 3",
            type = "Shushi Mock",
            picture = "https://images.pexels.com/photos/18078297/pexels-photo-18078297/free-photo-of-ciudad-calle-barra-urbano.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
            offers = listOf(
                Offer(
                    id = "b2e21a5e-958f-4ab8-84fe-7d78b63b9301",
                    offerName = "OfferNameMock3-1",
                    description = "Mock description3-1 mock Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, ",
                    image = "https://www.camarero10.com/wp-content/uploads/2020/02/como-distribuir-un-restaurante.jpg",
                    startTime = "2023-08-09T15:00:00Z",
                    endTime = "2023-08-09T17:00:00Z",
                    postTime = "22023-08-09T11:00:00Z"
                ),
                Offer(
                    id = "b2e21a5e-958f-4ab8-84fe-7d78b63b9302",
                    offerName = "OfferNameMock3-2",
                    description = "Mock description3-2 mock Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, ",
                    image = "https://www.camarero10.com/wp-content/uploads/2020/02/como-distribuir-un-restaurante.jpg",
                    startTime = "2023-08-09T15:00:00Z",
                    endTime = "2023-08-09T17:00:00Z",
                    postTime = "22023-08-09T11:00:00Z"
                ),
                Offer(
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

