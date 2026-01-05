package com.example.composeactivity.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composeactivity.UserSession
import com.example.composeactivity.data.entity.Examination
import com.example.composeactivity.data.entity.Specjalization
import com.example.composeactivity.repository.SpecjalizationRepository
import com.example.composeactivity.repository.SpecjalizationUserRepository
import com.example.composeactivity.utils.ToastManager
import com.example.composeactivity.utils.Utils.Companion.isNameOnList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsSpecjalizationViewModel @Inject constructor (private val repoSpec: SpecjalizationRepository,
                                                           private val repoSpecUser: SpecjalizationUserRepository) : ViewModel() {


    private val _specjalizations = MutableStateFlow<List<Specjalization>>(emptyList())
    val specjalizations: StateFlow<List<Specjalization>> = _specjalizations

    private val _activeSpecjalizations = MutableStateFlow<List<Specjalization>>(emptyList())
    val activeSpecjalizations: StateFlow<List<Specjalization>> = _activeSpecjalizations

    private var userId: Int by mutableStateOf(0)


    private val _showDialog = MutableStateFlow(false)
    val showDialog: StateFlow<Boolean> = _showDialog

    fun getShowDialog(): Boolean = showDialog.value
    fun setShowDialog(value: Boolean) {
        _showDialog.value = value
    }

    private val _addedAlert = MutableStateFlow<String?>(null)
    val addedAlert = _addedAlert.asStateFlow()

    fun getAddedAlert(): String? = _addedAlert.value
    fun setAddedAlert(value: String?) {
        _addedAlert.value = value
    }

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage = _errorMessage.asStateFlow()

    fun getErrorMessage(): String? = _errorMessage.value
    fun setErrorMessage(value: String?) {
        _errorMessage.value = value
    }



    init {
        viewModelScope.launch {
            fetchUserId()
            fetchsSpecjalizationsFromApi()
            fetchActiveSpecjalizationsFromApi()
        }
    }


    suspend fun fetchUserId() {
        userId = UserSession.getUserIdFlow().first()!!
    }

    suspend fun fetchsSpecjalizationsFromApi()  {
        val latest = repoSpec.getAllSpecjalizations().first().sortedBy {it.name}
        _specjalizations.value = latest
    }

    suspend fun fetchActiveSpecjalizationsFromApi()  {
        val latest = repoSpec.getSpecjalizations(userId).first().sortedBy {it.name}
        _activeSpecjalizations.value = latest
    }


    fun addSpecjalization(nameSpec : String) = viewModelScope.launch{
        try {

            if(nameSpec.isBlank()){
                setErrorMessage("Nazwa specjalizacji nie może być pusta")
                return@launch
            }

            val specjalizations =_specjalizations.value as? List<Object> ?: emptyList()

            if((isNameOnList(nameSpec, specjalizations)))
            {
                setErrorMessage("Specjalizacja juz istnieje poszukaj na liście")
                return@launch
            }

           val resp = repoSpec.addSpecjalization(userId,nameSpec)
            if(resp.isSuccessful)///// TUTAJ zapytaj code czy jest okej?
            {
                setShowDialog(false)
                clearToastMessage()
                setAddedAlert("Specjaliacja została dodana poprawnie, poczekaj na autoryzcję ok. 1-2 dni ")
            } else setErrorMessage(resp.message())



        } catch (e : Exception){
            Log.e("Error", "Fail added specjalization " + e)
            setErrorMessage("Wystąpił błąd podczas dodawania specjalizacji")
        }
    }


    fun addSpecjalization(specjalization: Specjalization) = viewModelScope.launch{
        try {
            repoSpecUser.addSpecjalizationUser(specjalization.id,userId)
            fetchActiveSpecjalizationsFromApi()
        } catch (e : Exception){ Log.e("Error", "Fail added specjalization " + e.printStackTrace()) }
    }

    fun deleteSpecjalization(specjalization: Specjalization) = viewModelScope.launch{
        try {
            repoSpecUser.deleteSpecjalizationUser(specjalization.id,userId)
            fetchActiveSpecjalizationsFromApi()
        } catch (e : Exception){ Log.e("Error", "Fail deleted specjalization " +  e.printStackTrace()) }
    }

    fun clearToastMessage() {
        _errorMessage.value = null
    }
}