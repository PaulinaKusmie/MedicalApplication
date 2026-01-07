package com.example.composeactivity.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.toLowerCase
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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@HiltViewModel
class RegisterViewModel @Inject constructor(private val userRespository : UserRepository) : ViewModel() {


    private val _showDialog = MutableStateFlow(false)
    val showDialog: StateFlow<Boolean> = _showDialog


    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email


    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password

    private val _name = MutableStateFlow("")
    val name: StateFlow<String> = _name


    private val _age = MutableStateFlow("")
    val age: StateFlow<String> = _age


    private val _message = MutableStateFlow("")
    val message: StateFlow<String> = _message

    private val _code = MutableStateFlow("")
    val code: StateFlow<String> = _code

    private val _messageCode = MutableStateFlow<String?>(null)
    val messageCode: StateFlow<String?> = _messageCode

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading



    fun getShowDialog(): Boolean = showDialog.value
    fun setShowDialog(value: Boolean) {
        _showDialog.value = value
    }

    fun updateCode(value: String) {
        _code.value = value
    }
    fun getCode(): String = _code.value

    fun updateMessage(value: String) {
        _message.value = value
    }
    fun getMessage(): String = _message.value

    fun updateAge(value: String) {
        _age.value = value
    }
    fun getAge(): String = _age.value

    fun updateName(value: String) {
        _name.value = value
    }
    fun getName(): String = _name.value

    fun updatePassword(value: String) {
        _password.value = value
    }
    fun getPassword(): String = _password.value

    fun updateEmail(value: String) {
        _email.value = value.trim()
    }
    fun getEmail(): String = _email.value

    fun updateMessageCode(value: String?) {
        _messageCode.value = value
    }
    fun getMessageCode(): String? = _messageCode.value

        private val navigationEvent = MutableSharedFlow<Boolean>()
        val NavigationEvent = navigationEvent.asSharedFlow()



        fun register() {
            viewModelScope.launch {
                try {
                    val ageInt = getAge().toIntOrNull()
                    when{
                        getEmail().isBlank() -> updateMessage("Podaj adres email")
                        getPassword().isBlank() -> updateMessage("Podaj hasło")
                        getName().isBlank() -> updateMessage("Podaj imię")
                        ageInt == null || ageInt <= 0 -> updateMessage("Podaj poprawny wiek")
                        else -> {

                            _isLoading.value = true

                            var user = User(
                                0,
                                getEmail(),
                                getPassword(),
                                getAge().toInt(),
                                getName(),
                                null
                            )
                            var result = userRespository.register(user)
//
                            val errorBody = result.errorBody()?.string()
                            val errorMessage = result.message()
                            val errorCode = result.code()
                            Log.d("dddd",errorBody.toString() + "   " + errorMessage.toString()   + "   " + errorCode.toString() )

                            if (result.isSuccessful) {
                                updateMessage("Zarejestrowano użytkownika")
                                delay(3000)
                                clearMessage()
                                setShowDialog(true)

                            } else{
                                updateMessage("Coś poszło nie tak! Spróbuj ponownie ${result.code() ?: " "}")
                                _isLoading.value = false
                            }

                        }
                    }

                }
                catch (e : Exception){
                    Log.e("Błąd", "Fail registration please try again: ${e.message ?: " "}" + e)
                    updateMessage("Coś poszło nie tak! Spróbuj ponownie ${e.message ?: " "}")
                    _isLoading.value = false
                }
            }
        }

          fun confirmUser()  =  viewModelScope.launch {
              try{

                  val codeInt = getCode().toIntOrNull()
                  if(getEmail().isNullOrBlank() || codeInt == null){
                      updateMessageCode("Podaj email i poprawny kod")
                      return@launch
                  }

                  var confirmAccountRequest = ConfirmAccountRequest(
                      getEmail(),
                      getCode().toInt())

                 val result = userRespository.confirmCode(confirmAccountRequest)

                  val errorBody = result.errorBody()?.string()
                  val errorMessage = result.message()
                  val errorCode = result.code()
                  Log.d("dddd",errorBody.toString() + "   " + errorMessage.toString()   + "   " + errorCode.toString() )
//
                  updateMessageCode(
                      (result.code() ?: "Nieprawidłowa odpowiedź serwera").toString()
                  )
                  if(result.isSuccessful)  navigationEvent.emit(true)
              }
              catch (e : Exception){
                  Log.e("Błąd", "Fail registration please try again  ${e.message ?: "Spróbuj ponownie"}" + e)
                  updateMessageCode("Coś poszło nie tak! Spróbuj ponownie ${e.message ?: ""}")
              }
          }

    fun clearMessage() {
        updateMessage("")
    }
}