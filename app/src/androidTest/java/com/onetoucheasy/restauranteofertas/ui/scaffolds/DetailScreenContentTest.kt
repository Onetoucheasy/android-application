package com.onetoucheasy.restauranteofertas.ui.scaffolds

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.onetoucheasy.restauranteofertas.repository.local.model.LocalOffer
import com.onetoucheasy.restauranteofertas.repository.local.model.LocalRestaurantShortInfo
import org.junit.Rule
import org.junit.Test

class DetailScreenContentTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun given_screen_when_launched_then_Details_displayed () {
        with(composeRule) {

            // Given
            setContent {
                DetailScreenContent(offer = offerSample2)
            }

            // When

            // Then
            onNodeWithText(text = offerSample2.offerName)
            onNodeWithText(text = offerSample2.description)
            onNodeWithText(text = offerSample2.restaurant.name)
        }
    }
}

val offerSample2 = LocalOffer(
    id = "123",
    restaurant = LocalRestaurantShortInfo(
        id = "789",
        name = "Tapas Restaurante Ejemplo"
    ),
    offerName = "10 por 1!",
    description = "¡Ben para disfruta la comida! Tenemos 10 por 1 solamente ésta tarde!",
    image = "https://cdn.alfabetajuega.com/alfabetajuega/2020/12/goku1.jpg?width=300",
    startTime = "1600",
    endTime = "1800",
    postTime = "1500",
)