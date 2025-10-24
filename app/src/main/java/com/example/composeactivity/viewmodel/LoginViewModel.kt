package com.example.composeactivity.viewmodel
import kotlinx.coroutines.*
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composeactivity.UserSession
import com.example.composeactivity.data.dto.LoginRequest
import com.example.composeactivity.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor (private val userRespository : UserRepository)
    : ViewModel() {

    var email by mutableStateOf("")

    var password by mutableStateOf("")

    var message by mutableStateOf("")

    private val navigationEvent = MutableSharedFlow<Boolean>()
    val NavigationEvent = navigationEvent.asSharedFlow()


    fun forgotPassword() {
        viewModelScope.launch {

        }
    }


     fun login() {
         viewModelScope.launch {
             try {
             if (email.isBlank() || password.isBlank()) {
                 message = "Wypełnij wszystkie pola"
             } else {
                 val loginRequest = LoginRequest(email, password)
                 var result = userRespository.login(loginRequest)
                  if (result.isSuccessful){
                      UserSession.saveUserId(result.body()?.id!!)
                      message = "Sucessful! hello " + result.body()?.name!!
                      delay(3000)
                      navigationEvent.emit(true)

                  }
                 else message = "Something went wrong! Try again!"
             }
             } catch(e: Exception) {
                 Log.e("API_ERROR", "Error calling login", e)
             }
         }
    }


    fun clearMessage() {
        message = ""
    }


}