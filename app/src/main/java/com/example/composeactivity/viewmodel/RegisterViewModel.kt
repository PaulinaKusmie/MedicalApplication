package com.example.composeactivity.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composeactivity.data.entity.User
import com.example.composeactivity.repository.UserRepository
import kotlinx.coroutines.launch
import java.time.LocalDateTime

class RegisterViewModel(private val userRespository : UserRepository) : ViewModel() {


    var email by mutableStateOf("")
        private set

    var password by mutableStateOf("")
        private set

    var name by mutableStateOf("")
    private set

    var age by mutableStateOf(0)
        private set

    var message by mutableStateOf("")




    fun register() {
        viewModelScope.launch {
            if (email.isBlank() || password.isBlank() || name.isBlank() || age == 0) {
                message = "Wypełnij wszystkie pola"
            } else {
                var user = User(
                    0,
                    email,
                    password,
                    age,
                    name,
                    Converter.localDateTimeToLong(LocalDateTime.now()
                    )
                )
               var result = userRespository.register(user)
                if(result.isSuccessful)
                message = "Zarejestrowano użytkownika"
                else
                    message = "Something went wrong! Try again!"
            }
        }
    }

    fun clearMessage() {
        message = ""
    }
}