package com.example.composeactivity.viewmodel

import android.app.Application
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.example.composeactivity.compose.EntryMode
import com.example.composeactivity.compose.UIStateObject.VisitDateUiState
import com.example.composeactivity.compose.VisitType
import com.example.composeactivity.data.AppDatabase
import com.example.composeactivity.data.entity.VisitDate
import com.example.composeactivity.repository.VisitDateRepository
import com.example.composeactivity.viewmodel.Converter
import com.example.composeactivity.viewmodel.Mapper.VisitDateMapper.Companion.toUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDateTime

class VisitDateViewModel (application: Application) : AndroidViewModel(application)  {

    private val repo =  VisitDateRepository(AppDatabase.get(application).visitDateDao())
    val dates = repo.allVisitDate.asLiveData()



    internal val dateVisitUI = mutableStateOf(VisitDateUiState())
    val DateVisitUI : State<VisitDateUiState> = dateVisitUI



    fun setMode(mode: EntryMode) {
        viewModelScope.launch {
            when (mode) {
                is EntryMode.AddSpecjalizationVisit -> {
                    val visitDate: VisitDate? = getVisitDate(mode.specjalizationId, VisitType.SPECIALIZATION)
                    val uiState = visitDate?.toUiState() ?: VisitDateUiState(foreignId = mode.specjalizationId, type = VisitType.SPECIALIZATION)
                    dateVisitUI.value = uiState.copy(name = mode.name)

                }

                is EntryMode.AddExaminationVisit -> {
                    val visitDate: VisitDate? = getVisitDate(mode.examinationId, VisitType.EXAMINATION)
                    val uiState = visitDate?.toUiState() ?: VisitDateUiState(foreignId = mode.examinationId, type = VisitType.EXAMINATION)
                    dateVisitUI.value = uiState.copy(name = mode.name)

                }
            }
        }
    }


    suspend fun getVisitDate(id: Int, type :VisitType): VisitDate? = withContext(Dispatchers.IO) {
        return@withContext repo.getVisitDate(id,type)
    }




     fun updateDoneDate(visitDate: VisitDate) = viewModelScope.launch{
         if(visitDate.id != 0)
            repo.updateDoneDate(visitDate)
         else
             repo.addVisit(visitDate)
    }
     fun updatePredictedDate(visitDate: VisitDate) = viewModelScope.launch{
       // repo.addVisit(visitDate)
    }
     fun updateAppointmentDate(visitDate: VisitDate) = viewModelScope.launch{
       // repo.addVisit(visitDate)
    }

    suspend fun saveVisit(visitDate: VisitDate) = viewModelScope.launch{
        repo.addVisit(visitDate)
    }


}
