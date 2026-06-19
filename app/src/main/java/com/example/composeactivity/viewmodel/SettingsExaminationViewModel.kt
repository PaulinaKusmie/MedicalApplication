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
import com.example.composeactivity.repository.ExaminationRepository
import com.example.composeactivity.repository.ExaminationUserRepository
import com.example.composeactivity.utils.ToastManager
import com.example.composeactivity.utils.Utils.Companion.isNameOnList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
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


    private val _showDialog = MutableStateFlow(false)
    val showDialog: StateFlow<Boolean> = _showDialog.asStateFlow()

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

    private var userId: Int by mutableStateOf(0)



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


    fun addExamination(nameExam : String) {
        viewModelScope.launch {
            try {
                if(nameExam.isBlank()) {
                setErrorMessage("Nazwa badania nie może być pusta")
                    return@launch
                }
                val examinations = _examinations.value as? List<Object> ?: emptyList()

                if ((isNameOnList(nameExam, examinations))) {
                    setErrorMessage("Badanie juz istnieje poszukaj na liście")
                    return@launch
                }

                val resp = repoExam.addExamination(userId, nameExam)
                if(resp.isSuccessful){
                    clearToastMessage()
                    setShowDialog(false)
                    setAddedAlert("Badanie zostało dodane poprawnie, poczekaj na autoryzcję ok. 1-2 dni")
                } else setErrorMessage(resp.message())



            } catch (e: Exception) {
                Log.e("Error", "Fail added examination " + e)
                setErrorMessage("Wystąpił błąd podczas dodawania badania")
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


    fun clearToastMessage() {
        _errorMessage.value = null
    }
}