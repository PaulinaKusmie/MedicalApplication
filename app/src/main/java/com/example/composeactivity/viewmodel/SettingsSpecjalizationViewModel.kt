package com.example.composeactivity.viewmodel

import android.util.Log
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
    var name: String by mutableStateOf("")

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

    fun addSpecjalization() = viewModelScope.launch{
        try {
            if(!(isNameOnList(name, (_specjalizations.value)as List<Object>)))
            {
                repoSpec.addSpecjalization(userId,name)
                fetchsSpecjalizationsFromApi()
                fetchActiveSpecjalizationsFromApi()
            }else{
                ToastManager.showToast("Specjalizacja juz istnieje poszukaj na liście")
            }
        } catch (e : Exception){ Log.e("Error", "Fail added examination " + e.printStackTrace()) }
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
}