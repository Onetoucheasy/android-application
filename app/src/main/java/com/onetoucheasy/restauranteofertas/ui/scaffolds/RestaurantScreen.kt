package com.onetoucheasy.restauranteofertas.ui.scaffolds

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.onetoucheasy.restauranteofertas.repository.mappers.RemoteToLocalMapper
import com.onetoucheasy.restauranteofertas.ui.viewModels.MainScreenViewModel

@Composable
fun RestaurantScreen (viewModel: MainScreenViewModel, id: String) {
    val offerState by viewModel.stateOffers.collectAsState()
    val restaurantState by viewModel.stateRestaurants.collectAsState()
    val remoteToLocalMapper = RemoteToLocalMapper() // only use this if wanting to convert LocalOffer to Offers for consistency
    val offer = offerState.find { it.id == id }
    LaunchedEffect(Unit){
        Log.d("Tag","RestaurantScreen...\nid: $id")
    }
    if (offer != null) {
        DetailScreenContent(offer = remoteToLocalMapper.mapLocalOfferToOffers(offer)) // todo: consider LocalOffer too
    }
}