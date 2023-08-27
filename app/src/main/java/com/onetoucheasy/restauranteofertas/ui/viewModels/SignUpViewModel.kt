package com.onetoucheasy.restauranteofertas.ui.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.auth0.android.jwt.JWT
import com.onetoucheasy.restauranteofertas.repository.Repository
import com.onetoucheasy.restauranteofertas.repository.UserType
import com.onetoucheasy.restauranteofertas.repository.remote.request.SignUpRequestBody
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

enum class SignUpState {
    NONE,
    SUCCESS,
    FAILURE
}
enum class SignUpType {
    NONE,
    COMPANY,
    USER
}
@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {

    private val _signUpState = MutableLiveData(SignUpState.NONE)
    private val _signUpType = MutableLiveData(SignUpType.NONE)
    val signUpState: LiveData<SignUpState> get() = _signUpState

    fun performSignUp(name: String, email: String, password: String, userType: UserType) {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                try {
                    var isCompany = ""
                    when(userType){
                        UserType.COMPANY-> isCompany = "company"
                        UserType.CUSTOMER-> isCompany = "customer"
                        else -> {}
                    }
                    val signUpBody = SignUpRequestBody(name, email, password, isCompany)
                    val jwt = repository.performSignUp(signUpBody)

                    val decodedJwt = jwt?.let {
                        JWT(it.accessToken).getClaim("accessToken").asString()
                    }
                    decodedJwt?.let {
                        _signUpState.postValue(SignUpState.SUCCESS)

                        if (isCompany == "company"){
                            _signUpType.postValue(SignUpType.COMPANY)
                        }else{
                            _signUpType.postValue(SignUpType.USER)
                        }
                    }
                } catch (exception: Exception) {
                    _signUpState.postValue(SignUpState.FAILURE)
                }
            }
        }
    }
}