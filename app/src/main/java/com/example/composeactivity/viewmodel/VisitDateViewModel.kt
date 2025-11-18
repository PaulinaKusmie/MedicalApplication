package com.example.composeactivity.viewmodel

import android.app.Application
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composeactivity.UserSession
import com.example.composeactivity.compose.Tools.DateType
import com.example.composeactivity.compose.Tools.EntryMode
import com.example.composeactivity.compose.UIStateObject.VisitDateUiState
import com.example.composeactivity.compose.Tools.VisitType
import com.example.composeactivity.data.entity.VisitDate
import com.example.composeactivity.repository.ReminderRepository
import com.example.composeactivity.repository.VisitDateRepository
import com.example.composeactivity.viewmodel.Mapper.VisitDateMapper.Companion.toUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class VisitDateViewModel @Inject constructor ( private val repo: VisitDateRepository) : ViewModel() {

    internal val dateVisitUI = mutableStateOf(VisitDateUiState())
    val DateVisitUI : State<VisitDateUiState> = dateVisitUI

    private var userId: Int by mutableStateOf(0)

    init {
        fetchUserId()
    }

    fun fetchUserId() = viewModelScope.launch {
        userId = UserSession.getUserIdOnce()!!
    }



    fun setMode(mode: EntryMode) {
        viewModelScope.launch {
            try {
                when (mode) {

                    is EntryMode.AddSpecjalizationVisit -> {
                        val visitDate: VisitDate? = getVisitDate(mode.specjalizationId, userId) //SPECIALIZATION
                        val uiState = visitDate?.toUiState() ?: VisitDateUiState(foreignId = mode.specjalizationId, type = VisitType.SPECIALIZATION)
                        dateVisitUI.value = uiState.copy(name = mode.name)

                    }

                    is EntryMode.AddExaminationVisit -> {
                        val visitDate: VisitDate? = getVisitDate(mode.examinationId,userId ) //VisitType.EXAMINATION
                        val uiState = visitDate?.toUiState() ?: VisitDateUiState(foreignId = mode.examinationId, type = VisitType.EXAMINATION)
                        dateVisitUI.value = uiState.copy(name = mode.name)

                    }
                }
            } catch (e : Exception) {
                Log.e("Error", "Fail loaded data")
            }
        }

    }


    suspend fun getVisitDate(id: Int, type :Int): VisitDate? = withContext(Dispatchers.IO) {
        return@withContext repo.getVisitDate(id, userId, type) // tutaj type zmień na INT
    }


     fun updateDoneDate(visitDate: VisitDate) {
         viewModelScope.launch {
             try{
             if (visitDate.id != 0) {
                 Log.w("DT", "clearDate: was null")
                 repo.updateDoneDate(visitDate)
             }
             else
             {
                 Log.e("API_ERROR addVisit", userId.toString())
                 repo.addVisit(visitDate)
             }

            } catch (e : Exception){ Log.e("Error", "on create or update DoneDate")}
         }
    }
     fun updatePredictedDate(visitDate: VisitDate) {
         viewModelScope.launch {
             try{
             if (visitDate.id != 0)
                 repo.updatePredictedDate(visitDate)
             else
                 repo.addVisit(visitDate)
             } catch (e : Exception){ Log.e("Error", "on create or update PredictedDate ")}
         }
    }
     fun updateAppointmentDate(visitDate: VisitDate) {
         viewModelScope.launch{
             try{
                 if(visitDate.id != 0)
                     repo.updateAppointmentDate(visitDate)
                 else
                     repo.addVisit(visitDate)
             } catch (e : Exception){ Log.e("Error", "on create or update AppointmentDate ")}

         }
    }

    fun clearDate(type:DateType) {
        var id = dateVisitUI.value.id
            if (id != null) {
                viewModelScope.launch{
                try {
                    repo.clearDate(id, userId, type)
                    ClearUI(type)
                }
                catch (e: Exception) { Log.e("DT", "clearDate: error clearing date", e) }
                }
            }
            else { Log.w("DT", "clearDate: was null") }
    }

    fun ClearUI(type:DateType) {

        when(type) {
            DateType.DONE -> dateVisitUI.value = dateVisitUI.value.copy(doneDate = null)
            DateType.PREDICTED -> dateVisitUI.value = dateVisitUI.value.copy(predictedDate = null)
            DateType.APPOITMENT -> dateVisitUI.value = dateVisitUI.value.copy(appointmentDate = null)
        }
    }

}
