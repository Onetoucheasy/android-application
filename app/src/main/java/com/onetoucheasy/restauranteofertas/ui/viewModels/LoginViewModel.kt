package com.onetoucheasy.restauranteofertas.ui.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.auth0.android.jwt.JWT
import com.onetoucheasy.restauranteofertas.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Credentials
import java.nio.charset.Charset
import javax.inject.Inject
enum class LoginState {
    NONE,
    SUCCESS,
    FAILURE
}
enum class LoginType {
    NONE,
    COMPANY,
    USER
}
@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {

    private val _loginState = MutableLiveData(LoginState.NONE)
    private val _loginType = MutableLiveData(LoginType.NONE)
    val loginState: LiveData<LoginState> get() = _loginState
    val loginType: LiveData<LoginType> get() = _loginType
    fun performLogin(email: String, password: String) {
        val headerAuthorizationData = Credentials.basic(email, password, Charset.defaultCharset())
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    val jwt = repository.performLogin(headerAuthorizationData)

                    val decodedJwt = jwt?.let { response ->
                        JWT(response.accessToken).getClaim("isCompany").asBoolean()
                    }
                    if(decodedJwt == true){
                        _loginType.postValue(LoginType.COMPANY)
                    }else{
                        _loginType.postValue(LoginType.USER)
                    }
                    _loginState.postValue(LoginState.SUCCESS)

                } catch (exception: Exception) {
                    _loginState.postValue(LoginState.FAILURE)
                    Log.d("Exception login", "${exception.message}")
                }
            }
        }
    }
}



