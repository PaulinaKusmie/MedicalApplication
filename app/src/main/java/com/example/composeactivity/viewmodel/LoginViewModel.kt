package com.example.composeactivity.viewmodel
import kotlinx.coroutines.*
import android.util.Log
import androidx.compose.runtime.State
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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor (private val userRespository : UserRepository)
    : ViewModel() {

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password.asStateFlow()

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()


    fun setPassword(value: String) {
        _password.value = value
    }

    fun setEmail(value: String) {
        _email.value = value
    }

    private val navigationEvent = MutableSharedFlow<Boolean>()
    val NavigationEvent = navigationEvent.asSharedFlow()

    fun forgotPassword() {
        viewModelScope.launch {

        }
    }


     fun login() {
         viewModelScope.launch {

             if (_email.value.isBlank() || _password.value.isBlank()) {
                 _uiState.value = LoginUiState(
                     isLoading = false,
                     message = "Wypełnij wszystkie pola"
                 )
                 return@launch
             }

             try {
                 _uiState.value = LoginUiState(isLoading = true)
                 val loginRequest = LoginRequest(email.value, password.value)
                 var result = userRespository.login(loginRequest)
                  if (result.isSuccessful){
                      result.body()?. let{
                          UserSession.saveUserId(it?.id ?: 0)
                          navigationEvent.emit(true)
                      } ?:{
                          _uiState.value = LoginUiState(
                              isLoading = false,
                              message = "Błąd: brak danych użytkownika"
                          )
                      }
                  } else {
                      _uiState.value = LoginUiState(
                          isLoading = false,
                          message = "Nieprawidłowy email lub hasło"
                      )
                  }

             } catch(e: Exception) {
                 Log.e("LoginViewModel ", "Error login "+ e)
                 _uiState.value = LoginUiState(
                     isLoading = false,
                     message = "Wystąpił błąd: ${e.localizedMessage ?: "Spróbuj ponownie"}"
                 )
             }
             finally {
                 _uiState.value = LoginUiState(isLoading = false)
             }
         }
    }


    fun clearMessage() {
        _uiState.value = LoginUiState(message = "")
    }


}

data class LoginUiState(
    val isLoading : Boolean = false,
    val message : String? = null,
    val isSuccess: Boolean = false
)