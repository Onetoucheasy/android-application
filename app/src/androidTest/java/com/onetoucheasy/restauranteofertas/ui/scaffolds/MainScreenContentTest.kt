package com.onetoucheasy.restauranteofertas.ui.scaffolds

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.onetoucheasy.restauranteofertas.MainActivity
import com.onetoucheasy.restauranteofertas.repository.localRestaurantListMock
import com.onetoucheasy.restauranteofertas.repository.offerListMock
import org.junit.Rule
import org.junit.Test

class MainScreenContentTest {

    @get:Rule
    val composeRule = createComposeRule()

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    private val onOfferClick: (String) -> Unit = {}
    private val onRestaurantClick: (String) -> Unit = {}
    private val onOfferFavClick: (String) -> Unit = {}

    @Test
    fun given_screen_when_launched_then_Details_displayed () {
        with(composeRule) {

            // Given
            setContent {
                MainScreenContent(
                    offers = offerListMock,
                    restaurants = localRestaurantListMock,
                    onOfferClicked = onOfferClick,
                    onRestaurantClicked = onRestaurantClick,
                    onOfferFavClicked = onOfferFavClick
                )
            }

            // Then

            onNodeWithText("Ofertas").assertIsDisplayed() // pass 20230909 15:49 CET
            onNodeWithText("Restaurantes").assertIsDisplayed()// pass 20230909 15:49 CET
        }
    }
}