package com.onetoucheasy.restauranteofertas.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.onetoucheasy.restauranteofertas.repository.Repository
import com.onetoucheasy.restauranteofertas.repository.local.model.LocalOffer
import com.onetoucheasy.restauranteofertas.repository.local.model.LocalRestaurant
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {

    private val _stateOffers = MutableStateFlow<List<LocalOffer>>(emptyList())
    val stateOffers: StateFlow<List<LocalOffer>> get() = _stateOffers

    private val _stateRestaurants = MutableStateFlow<List<LocalRestaurant>>(emptyList())
    val stateRestaurants: StateFlow<List<LocalRestaurant>> get() = _stateRestaurants

    fun getOffers() {
        viewModelScope.launch {
            repository.getOfferList().collect { offers ->
                _stateOffers.value = offers
            }
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