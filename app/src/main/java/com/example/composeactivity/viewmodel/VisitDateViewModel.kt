package com.example.composeactivity.viewmodel

import android.app.Application
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.composeactivity.compose.EntryMode
import com.example.composeactivity.compose.UIStateObject.VisitDateUiState
import com.example.composeactivity.data.AppDatabase
import com.example.composeactivity.data.entity.VisitDate
import com.example.composeactivity.repository.VisitDateRepository
import kotlinx.coroutines.launch

class VisitDateViewModel (application: Application) : AndroidViewModel(application)  {

    private val repo =  VisitDateRepository(AppDatabase.get(application).visitDateDao())
    val dates = repo.allVisitDate.asLiveData()

    suspend fun saveVisit(visitDate: VisitDate) = viewModelScope.launch{
        repo.addVisit(visitDate)
    }

    private val dateVisitUI = mutableStateOf(VisitDateUiState())
    val DateVisitUI : State<VisitDateUiState> = dateVisitUI


    fun setMode(mode : EntryMode){
        when(mode)
        {
            is EntryMode.AddSpecjalizationVisit -> {
                dateVisitUI.value = VisitDateUiState(specjalizationId = mode.specjalizationId)
            }

            is EntryMode.AddExaminationVisit -> {

            }
            is EntryMode.Edit -> {


            }
        }
    }
}