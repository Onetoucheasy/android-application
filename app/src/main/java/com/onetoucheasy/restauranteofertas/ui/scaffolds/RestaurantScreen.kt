package com.onetoucheasy.restauranteofertas.ui.scaffolds

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.onetoucheasy.restauranteofertas.ui.viewModels.MainScreenViewModel

@Composable
fun RestaurantScreen (viewModel: MainScreenViewModel, id: String) {
    val offerState by viewModel.stateOffers.collectAsState()
    val restaurantState by viewModel.stateRestaurants.collectAsState()

    val offer = offerState.find { it.id == id }
    LaunchedEffect(Unit){
        Log.d("Tag","DetailScreen...")
    }
    if (offer != null) {
        DetailScreenContent(offer = offer)
    }
}