package com.example.composeactivity.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composeactivity.data.entity.User
import com.example.composeactivity.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlinx.coroutines.*

@HiltViewModel
class RegisterViewModel @Inject constructor(private val userRespository : UserRepository) : ViewModel() {


    var email by mutableStateOf("")

    var password by mutableStateOf("")

    var name by mutableStateOf("")

    var age by mutableStateOf("")

    var message by mutableStateOf("")

    private val navigationEvent = MutableSharedFlow<Boolean>()
    val NavigationEvent = navigationEvent.asSharedFlow()



    fun register() {
        viewModelScope.launch {
            if (email.isBlank() || password.isBlank() || name.isBlank() || age.toInt() == 0) {
                message = "Wypełnij wszystkie pola"
            } else {
                var user = User(
                    0,
                    email,
                    password,
                    age.toInt() ,
                    name,
                    null
                )
               var result = userRespository.register(user)
                if(result.isSuccessful) {
                    message = "Zarejestrowano użytkownika"
                    delay(3000)
                    navigationEvent.emit(true)
                }
                else
                    message = "Something went wrong! Try again!"
            }
        }
    }

    fun clearMessage() {
        message = ""
    }
}