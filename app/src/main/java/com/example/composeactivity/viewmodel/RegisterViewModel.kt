package com.example.composeactivity.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composeactivity.data.dto.ConfirmAccountRequest
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


    private val _showDialog = mutableStateOf(false)
    val showDialog: State<Boolean> = _showDialog

    fun getShowDialog(): Boolean = showDialog.value
    fun setShowDialog(value: Boolean) {
        _showDialog.value = value
    }

    private val _email = mutableStateOf("")
    val email: State<String> = _email

    fun updateEmail(value: String) {
        _email.value = value
    }
    fun getEmail(): String = _email.value




    private val _password = mutableStateOf("")
    val password: State<String> = _password

    fun updatePassword(value: String) {
        _password.value = value
    }
    fun getPassword(): String = _password.value



    private val _name = mutableStateOf("")
    val name: State<String> = _name

    fun updateName(value: String) {
        _name.value = value
    }
    fun getName(): String = _name.value



    private val _age = mutableStateOf("")
    val age: State<String> = _age

    fun updateAge(value: String) {
        _age.value = value
    }
    fun getAge(): String = _age.value



    private val _message = mutableStateOf("")
    val message: State<String> = _message

    fun updateMessage(value: String) {
        _message.value = value
    }
    fun getMessage(): String = _message.value



    private val _code = mutableStateOf("")
    val code: State<String> = _code

    fun updateCode(value: String) {
        _code.value = value
    }
    fun getCode(): String = _code.value



    private val _messageCode = mutableStateOf<String?>(null)
    val messageCode: State<String?> = _messageCode

    fun updateMessageCode(value: String?) {
        _messageCode.value = value
    }
    fun getMessageCode(): String? = _messageCode.value


    private val navigationEvent = MutableSharedFlow<Boolean>()
    val NavigationEvent = navigationEvent.asSharedFlow()



        fun register() {
            try {
                viewModelScope.launch {
                    if (getEmail().isBlank() || getPassword().isBlank() || getName().isBlank() || getAge().toInt() == 0) {
                        updateMessage("Wypełnij wszystkie pola")
                    } else {
                        var user = User(
                            0,
                            getEmail(),
                            getPassword(),
                            getAge().toInt(),
                            getName(),
                            null
                        )
                        var result = userRespository.register(user)
                        if (result.isSuccessful) {
                            updateMessage("Zarejestrowano użytkownika")
                            delay(3000)
                            setShowDialog(true)
                        } else
                            updateMessage("Coś poszło nie tak! Spróbuj ponownie")
                    }
                }
            }
            catch (e : Exception){ Log.e("Error", "Fail registration please try again " + e.printStackTrace()) }
        }

      fun confirmUser()  =  viewModelScope.launch {
        var confirmAccountRequest = ConfirmAccountRequest(getEmail(), getCode().toInt())
        updateMessageCode(userRespository.confirmCode(confirmAccountRequest).toString())
      }

    fun clearMessage() {
        updateMessage("")
    }
}