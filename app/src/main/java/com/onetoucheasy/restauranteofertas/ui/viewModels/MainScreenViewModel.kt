package com.onetoucheasy.restauranteofertas.ui.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.onetoucheasy.restauranteofertas.repository.Repository
import com.onetoucheasy.restauranteofertas.repository.local.model.LocalOffer
import com.onetoucheasy.restauranteofertas.repository.local.model.LocalRestaurant
import com.onetoucheasy.restauranteofertas.repository.remote.response.Offers
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

    fun getOffersList() {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO){
                repository.getOfferList()
            }
            _stateOffers.update { result }
        }
    }

    fun getRestaurantList() {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO){
                repository.getRestaurantList()
            }
            _stateRestaurants.update { result }
        }
    }
}