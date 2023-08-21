package com.onetoucheasy.restauranteofertas.ui.viewModels

import androidx.lifecycle.ViewModel
import com.onetoucheasy.restauranteofertas.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {


}