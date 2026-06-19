package com.example.composeactivity.viewmodel

import android.util.Log
import androidx.compose.material3.rememberSwipeToDismissBoxState
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

    private val _uiState = MutableStateFlow(RegisterUiState())
    val uiState: StateFlow<RegisterUiState> = _uiState

    private val _uiStateCode = MutableStateFlow(RegisterUiState())
    val uiStateCode: StateFlow<RegisterUiState> = _uiStateCode


//    private val _message = MutableStateFlow("")
//    val message: StateFlow<String> = _message

    private val _code = MutableStateFlow("")
    val code: StateFlow<String> = _code
//
//    private val _messageCode = MutableStateFlow<String?>(null)
//    val messageCode: StateFlow<String?> = _messageCode
//
//    private val _isLoading = MutableStateFlow(false)
//    val isLoading: StateFlow<Boolean> = _isLoading

    fun getShowDialog(): Boolean = showDialog.value
    fun setShowDialog(value: Boolean) {
        _showDialog.value = value
    }

    fun updateCode(value: String) {
        _code.value = value
    }
    fun getCode(): String = _code.value

//    fun updateMessage(value: String) {
//        _uiState.value.message = value
//    }

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

//    fun updateMessageCode(value: String?) {
//        _messageCode.value = value
//    }
//    fun getMessageCode(): String? = _messageCode.value

        private val navigationEvent = MutableSharedFlow<Boolean>()
        val NavigationEvent = navigationEvent.asSharedFlow()



        fun register() {
            viewModelScope.launch {
                try {
                    val ageInt = getAge().toIntOrNull()
                    when{
                        getEmail().isBlank() -> _uiState.value = RegisterUiState(message = "Podaj adres email", isSuccess = false)
                        getPassword().isBlank() ->  _uiState.value = RegisterUiState(message = "Podaj hasło", isSuccess = false)
                        getName().isBlank() ->   _uiState.value = RegisterUiState(message = "Podaj imię", isSuccess = false)
                        ageInt == null || ageInt <= 0 ->  _uiState.value = RegisterUiState(message = "Podaj poprawny wiek", isSuccess = false)
                        else -> {

                            _uiState.value = RegisterUiState(isLoading = true)

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
                                val body = result.body()
                                if(body?.success == true){
                                    _uiState.value = RegisterUiState(message = body.message, isLoading = false, isSuccess = true)
                                    delay(3000)
                                    _uiState.value.clearMessage()
                                    setShowDialog(true)
                                }else{
                                    _uiState.value = RegisterUiState(message = "Coś poszło nie tak! Spróbuj ponownie ${ body?.message ?: " "}", isLoading = false, isSuccess = false)
                                }
                            }

                        }
                    }

                }
                catch (e : Exception){
                    Log.e("Błąd", "Fail registration please try again: ${e.message ?: " "}" + e)
                    _uiState.value = RegisterUiState(message = "Coś poszło nie tak! Spróbuj ponownie ${e.message ?: " "}", isLoading = false, isSuccess = false)
                }
            }
        }

        fun confirmUser()  {
            try{
                viewModelScope.launch {

                        val codeInt = getCode().toIntOrNull()
                        if(getEmail().isNullOrBlank() || codeInt == null){
                            _uiStateCode.value = RegisterUiState(message = "Podaj email i poprawny kod", isLoading = false, isSuccess = false )
                            return@launch
                        }

                        _uiStateCode.value = RegisterUiState(isLoading = true)
                        var confirmAccountRequest = ConfirmAccountRequest(
                            getEmail(),
                            getCode().toInt())

                        val result = userRespository.confirmCode(confirmAccountRequest)

                        if(result.isSuccessful){
                           val body = result.body()
                            if (body?.success == true) {
                                _uiStateCode.value = RegisterUiState(message = body.message , isLoading = false, isSuccess = true )
                                delay(3000)
                                navigationEvent.emit(true)
                            } else {
                                _uiStateCode.value = RegisterUiState(message = body?.message ?: "Nieprawidłowa odpowiedź serwera", isLoading = false, isSuccess = false )
                            }

                        }
                         else _uiStateCode.value = RegisterUiState(message = "Coś poszło nie tak! Spróbuj ponownie", isLoading = false, isSuccess = false )

                }
            }
            catch (e : Exception){

                _uiStateCode.value = RegisterUiState(message = "Coś poszło nie tak! Spróbuj ponownie ${e.message ?: ""}", isLoading = false, isSuccess = false )
                Log.e("Błąd", "Fail registration please try again  ${e.message ?: "Spróbuj ponownie"}" + e)

            }
        }



}

data class RegisterUiState(
    val isLoading : Boolean = false,
    val message : String? = null,
    val isSuccess: Boolean = false
){
    fun clearMessage() : RegisterUiState{
        return copy(message = null)
    }
}