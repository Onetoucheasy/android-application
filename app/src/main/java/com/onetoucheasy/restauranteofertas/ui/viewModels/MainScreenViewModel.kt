package com.onetoucheasy.restauranteofertas.ui.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.onetoucheasy.restauranteofertas.repository.Repository
import com.onetoucheasy.restauranteofertas.repository.local.model.LocalOffer
import com.onetoucheasy.restauranteofertas.repository.local.model.LocalRestaurant
import com.onetoucheasy.restauranteofertas.repository.offerMock12
import com.onetoucheasy.restauranteofertas.repository.remote.response.Offer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {

    private val _stateOffers = MutableStateFlow<List<LocalOffer>>(emptyList())
    val stateOffers: StateFlow<List<LocalOffer>> get() = _stateOffers

    private val _stateRestaurants = MutableStateFlow<List<LocalRestaurant>>(emptyList())
    val stateRestaurants: StateFlow<List<LocalRestaurant>> get() = _stateRestaurants

    private val _stateDetail = MutableStateFlow<Offer>(offerMock12)
    val detailState: StateFlow<Offer> get() = _stateDetail

    fun getOffers() {
        viewModelScope.launch {
            repository.getOfferList().collect { offers ->
                _stateOffers.value = offers
            }
        }
    }
    fun getOfferById(offerId: String) {
        viewModelScope.launch {
//            repository.getOfferById(offerId) { offers ->
//                _stateDetail.value = offers
//            }
            val result = withContext(Dispatchers.IO) {
                repository.getOfferById(offerId)
            }
            _stateDetail.update { result }
            Log.d("Tag", "⭐️ _stateDetail: ${_stateDetail.value}")
        }
    }
    fun getRestaurants() {
        viewModelScope.launch {
            repository.getRestaurantList().collect { restaurants ->
                _stateRestaurants.value = restaurants
            }
        }
    }
}