package com.example.composeactivity.viewmodel

import android.app.Application
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.composeactivity.UserSession
import com.example.composeactivity.data.entity.Reminder
import com.example.composeactivity.data.entity.Specjalization
import com.example.composeactivity.repository.ReminderRepository
import com.example.composeactivity.repository.SpecjalizationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.Int

@HiltViewModel
class SpecjalizationViewModel @Inject constructor (private val repo: SpecjalizationRepository) : ViewModel() {

    private val _specjalizations = MutableStateFlow<List<Specjalization>>(emptyList())
    val specjalizations: StateFlow<List<Specjalization>> = _specjalizations

    private var userId: Int by mutableStateOf(0)

    init {
        viewModelScope.launch {
            fetchUserId()
            fetchsSpecjalizationsFromApi()
        }
    }

    suspend fun fetchUserId() {
        userId = UserSession.getUserIdFlow().first()!!
    }

    suspend fun fetchsSpecjalizationsFromApi()  {
        val latest = repo.getSpecjalizations(userId).first().sortedBy {it.name}
        _specjalizations.value = latest
    }

}


