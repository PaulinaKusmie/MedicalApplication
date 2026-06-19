package com.example.composeactivity.viewmodel

import android.app.Application
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.example.composeactivity.repository.ExaminationRepository
import kotlinx.coroutines.launch
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.composeactivity.UserSession
import com.example.composeactivity.data.entity.Examination
import com.example.composeactivity.data.entity.Reminder
import com.example.composeactivity.data.entity.Specjalization
import com.example.composeactivity.repository.ReminderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import javax.inject.Inject

@HiltViewModel
class ExaminationViewModel @Inject constructor (private val repo: ExaminationRepository) : ViewModel() {

    private val _examinations = MutableStateFlow<List<Examination>>(emptyList())
    val examinations: StateFlow<List<Examination>> = _examinations

    private var userId: Int by mutableStateOf(0)

    init {
        viewModelScope.launch {
            fetchUserId()
            fetchExaminationsFromApi()
        }
    }

    suspend fun fetchUserId() {
        userId = UserSession.getUserIdFlow().first()!!
    }

    suspend fun fetchExaminationsFromApi()  {
        val latest = repo.getExamination(userId).first().sortedBy {it.name}
        _examinations.value = latest
    }


}