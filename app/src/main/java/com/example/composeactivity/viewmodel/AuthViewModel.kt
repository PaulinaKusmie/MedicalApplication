package com.example.composeactivity.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.composeactivity.data.dto.LoginRequest
import com.example.composeactivity.repository.UserRepository

class AuthViewModel(val userRespository : UserRepository ) : ViewModel() {

    var email by mutableStateOf("")
        private set
    var password by mutableStateOf("")
        private set
    var message by mutableStateOf("")

    fun onEmailChange(newEmail: String) {
        email = newEmail
    }

    fun onPasswordChange(newPassword: String) {
        password = newPassword
    }

    suspend fun login() {
        if (email.isBlank() || password.isBlank()) {
            message = "Wypełnij wszystkie pola"
        } else {
            val loginRequest =  LoginRequest(email,password)
            var result = userRespository.login(loginRequest)
            if(result.isSuccessful)
            message = "Sucessful"
            else message = "Something went wrong! Try again!"
        }
    }

    fun register() {
        if (email.isBlank() || password.isBlank()) {
            message = "Wypełnij wszystkie pola"
        } else {
            // Tu logika rejestracji, np. wywołanie API
            message = "Zarejestrowano użytkownika: $email"
        }
    }

    fun clearMessage() {
        message = ""
    }
}