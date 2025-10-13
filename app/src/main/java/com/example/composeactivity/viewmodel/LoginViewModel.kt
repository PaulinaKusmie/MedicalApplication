package com.example.composeactivity.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composeactivity.data.dto.LoginRequest
import com.example.composeactivity.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor (private val userRespository : UserRepository)
    : ViewModel() {

    var email by mutableStateOf("")
        private set
    var password by mutableStateOf("")
        private set
    var message by mutableStateOf("")

    fun forgotPassword() {
        viewModelScope.launch {
            if (email.isBlank()) {
                message = "Wpisz adres mailowy"
            }
            userRespository.forgotPassword(email)
            message = "Check your email"
        }
    }


     fun login() {
         viewModelScope.launch {
             if (email.isBlank() || password.isBlank()) {
                 message = "Wypełnij wszystkie pola"
             } else {
                 val loginRequest = LoginRequest(email, password)
                 var result = userRespository.login(loginRequest)
                 if (result.isSuccessful)
                     message = "Sucessful"
                 else message = "Something went wrong! Try again!"
             }
         }
    }

    fun clearMessage() {
        message = ""
    }

}