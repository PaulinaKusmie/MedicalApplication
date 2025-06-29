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
import com.example.composeactivity.data.AppDatabase
import com.example.composeactivity.data.entity.VisitDate
import com.example.composeactivity.repository.VisitDateRepository
import com.example.composeactivity.viewmodel.Converter
import com.example.composeactivity.viewmodel.Mapper.VisitDateMapper.Companion.toUiState
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.time.LocalDateTime

class VisitDateViewModel (application: Application) : AndroidViewModel(application)  {

    private val repo =  VisitDateRepository(AppDatabase.get(application).visitDateDao())
    val dates = repo.allVisitDate.asLiveData()



    internal val dateVisitUI = mutableStateOf(VisitDateUiState())
    val DateVisitUI : State<VisitDateUiState> = dateVisitUI




    suspend fun setMode(mode : EntryMode){
        when(mode)
        {
            is EntryMode.AddSpecjalizationVisit -> {
                val visitDate: VisitDate? = getVisitDate(mode.specjalizationId)
                dateVisitUI.value = visitDate?.toUiState()!!
                dateVisitUI.value.name = mode.name
            }

            is EntryMode.AddExaminationVisit -> {
                val visitDate: VisitDate? = getVisitDate(mode.examinationId)
                dateVisitUI.value = visitDate?.toUiState()!!
                dateVisitUI.value.name = mode.name
            }

        }
    }

    suspend fun getVisitDate(id: Int) : VisitDate? {
        return  repo.getVisitDate(id)
    }



    suspend fun updateDoneDate(visitDate: Long) = viewModelScope.launch{
        repo.updateVisitDate(visitDate)
    }
    suspend fun updatePredictedDate(visitDate: VisitDate) = viewModelScope.launch{
        repo.updateVisitDate(visitDate)
    }
    suspend fun updateAppointmentDate(visitDate: VisitDate) = viewModelScope.launch{
        repo.updateVisitDate(visitDate)
    }

    suspend fun saveVisit(visitDate: VisitDate) = viewModelScope.launch{
        repo.addVisit(visitDate)
    }


}
