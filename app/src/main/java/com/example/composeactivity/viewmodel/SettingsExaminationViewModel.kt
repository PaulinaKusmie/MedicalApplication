package com.example.composeactivity.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composeactivity.UserSession
import com.example.composeactivity.data.entity.Examination
import com.example.composeactivity.repository.ExaminationRepository
import com.example.composeactivity.repository.ExaminationUserRepository
import com.example.composeactivity.utils.ToastManager
import com.example.composeactivity.utils.Utils.Companion.isNameOnList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.text.Normalizer
import java.util.Objects
import javax.inject.Inject

@HiltViewModel
class SettingsExaminationViewModel @Inject constructor (private val repoExam: ExaminationRepository,
                                                        private val repoExamUser: ExaminationUserRepository) : ViewModel()  {

    private val _examinations = MutableStateFlow<List<Examination>>(emptyList())
    val examinations: StateFlow<List<Examination>> = _examinations


    private val _activeExaminations = MutableStateFlow<List<Examination>>(emptyList())
    val activeExaminations: StateFlow<List<Examination>> = _activeExaminations


    private var userId: Int by mutableStateOf(0)
     var name: String by mutableStateOf("")

    init {
        viewModelScope.launch {
            fetchUserId()
            fetchExaminationsFromApi()
            fetchActiveExaminationsFromApi()
        }
    }

    suspend fun fetchUserId() {
        userId = UserSession.getUserIdFlow().first()!!
    }

    suspend fun fetchExaminationsFromApi()  {
        val latest = repoExam.getAllExamination().first().sortedBy {it.name}
        _examinations.value = latest
    }

    suspend fun fetchActiveExaminationsFromApi()  {
        val latest = repoExam.getExamination(userId).first().sortedBy {it.name}
        _activeExaminations.value = latest
    }


    fun addExaminationForUser(exam: Examination) = viewModelScope.launch{
        try {
            val response = repoExamUser.addExaminationUser(exam.id, userId)
            fetchActiveExaminationsFromApi()
        } catch (e : Exception){ Log.e("Error", "Fail added examination for user " + e.printStackTrace()) }
    }


    fun addExamination() {
        viewModelScope.launch {
            try {

                if (!(isNameOnList(name, (_examinations.value as List<Object>)))) {
                    repoExam.addExamination(userId, name)
                    fetchExaminationsFromApi()
                    fetchActiveExaminationsFromApi()
                } else {
                    ToastManager.showToast("Badanie juz istnieje poszukaj na liście")
                }
            } catch (e: Exception) {
                Log.e("Error", "Fail added examination " + e.printStackTrace())
            }
        }
    }



    fun deleteExamination(exam: Examination) = viewModelScope.launch {
            try {
                 repoExamUser.deleteExaminationUser(exam.id, userId)
                fetchActiveExaminationsFromApi()

            } catch (e: Exception) {
                Log.e("Error", "Fail deleted examination" + e.printStackTrace())
            }
    }
}