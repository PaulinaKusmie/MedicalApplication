package com.example.composeactivity.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composeactivity.UserSession
import com.example.composeactivity.compose.Tools.DateType
import com.example.composeactivity.compose.Tools.EntryMode
import com.example.composeactivity.compose.UIStateObject.VisitDateUiState
import com.example.composeactivity.compose.Tools.VisitType
import com.example.composeactivity.data.entity.VisitDate
import com.example.composeactivity.data.entity.VisitDateDTO
import com.example.composeactivity.repository.VisitDateRepository
import com.example.composeactivity.viewmodel.Mapper.VisitDateMapper.Companion.toDTO
import com.example.composeactivity.viewmodel.Mapper.VisitDateMapper.Companion.toDomain
import com.example.composeactivity.viewmodel.Mapper.VisitDateMapper.Companion.toUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class VisitDateViewModel @Inject constructor ( private val repo: VisitDateRepository) : ViewModel() {

    internal val dateVisitUI = mutableStateOf(VisitDateUiState())
    val DateVisitUI : State<VisitDateUiState> = dateVisitUI

    internal var userId: Int by mutableStateOf(0)

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
                        val visitDate: VisitDate? = getVisitDate(mode.specjalizationId, 0) //SPECIALIZATION
                        val uiState = visitDate?.toUiState() ?: VisitDateUiState(foreignId = mode.specjalizationId, type = VisitType.SPECIALIZATION, userId = userId)
                        dateVisitUI.value = uiState.copy(name = mode.name)
                    }

                    is EntryMode.AddExaminationVisit -> {
                        val visitDate: VisitDate? = getVisitDate(mode.examinationId, 1 ) //VisitType.EXAMINATION
                        val uiState = visitDate?.toUiState() ?: VisitDateUiState(foreignId = mode.examinationId, type = VisitType.EXAMINATION, userId = userId)
                        dateVisitUI.value = uiState.copy(name = mode.name)

                    }
                }
            } catch (e : Exception) {
                Log.e("Error", "Fail loaded data " + e.printStackTrace())
            }
        }

    }


    suspend fun getVisitDate(forgeinid: Int, type :Int): VisitDate? {
        var visitDate : VisitDate? = null
            try {
                visitDate = repo.getVisitDate(forgeinid, type, userId).toDomain()
            } catch (e: Exception) {
                Log.e("Error", "on getVisitDate + " + e.printStackTrace() )
            }
        return visitDate
    }



    fun updateDoneDate(visitDate: VisitDate) {
         viewModelScope.launch {
             try{

                 Log.d("Error", "co tam? " +  visitDate.toString())
             if (visitDate.id != 0) {
                 repo.updateDoneDate(visitDate.toDTO())
             }
             else
             {
                 repo.addVisit(visitDate.toDTO())
             }

            } catch (e : Exception){ Log.e("Error", "on create or update DoneDate " + e.printStackTrace())}
         }
    }
     fun updatePredictedDate(visitDate: VisitDate) {
         viewModelScope.launch {
             try{
             if (visitDate.id != 0) repo.updatePredictedDate(visitDate.toDTO())
             else repo.addVisit(visitDate.toDTO())
             } catch (e : Exception){ Log.e("Error", "on create or update PredictedDate " + e.printStackTrace())}
         }
    }
     fun updateAppointmentDate(visitDate: VisitDate) {
         viewModelScope.launch{
             try{
                 if(visitDate.id != 0)
                     repo.updateAppointmentDate(visitDate.toDTO())
                 else

                     repo.addVisit(visitDate.toDTO())
             } catch (e : Exception){ Log.e("Error", "on create or update AppointmentDate " + e.printStackTrace())}

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
                catch (e: Exception) { Log.e("DT", "clearDate: error clearing date" + e.printStackTrace()) }
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
