package com.onetoucheasy.restauranteofertas.ui.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.onetoucheasy.restauranteofertas.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Credentials
import java.nio.charset.Charset
import javax.inject.Inject
enum class LoginState{
    NONE,
    SUCCESS,
    FAILURE
}
@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {

//    private val _loginState = MutableStateFlow(false)
//    val loginState: StateFlow<Boolean> get() = _loginState


//    private val _loginState = MutableLiveData(false)
//    val loginState: LiveData<Boolean> get() = _loginState
    private val _loginState = MutableLiveData(LoginState.NONE)
    val loginState: LiveData<LoginState> get() = _loginState

    fun performLogin(email: String, password: String){
        //Encoding + Charset
        val headerAuthorizationData = Credentials.basic(email, password, Charset.defaultCharset())
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO){
                repository.performLogin(headerAuthorizationData)
            }
            if(result){
                _loginState.value = LoginState.SUCCESS
            }else{
                _loginState.value = LoginState.FAILURE
            }
          //  _loginState.value = result
        }
    }
}

