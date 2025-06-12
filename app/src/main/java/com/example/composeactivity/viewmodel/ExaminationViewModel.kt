package com.example.composeactivity.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.composeactivity.data.AppDatabase
import com.example.composeactivity.repository.ExaminationRepository
import com.example.composeactivity.repository.SpecjalizationRepository
import kotlinx.coroutines.launch

class ExaminationViewModel (application: Application) : AndroidViewModel(application) {

    private val repo =  ExaminationRepository(AppDatabase.get(application).examinationDao())
    val examinations = repo.allExamination.asLiveData()

    fun updateIsActive(id: Int, isActive: Boolean) =  viewModelScope.launch {
        repo.updateActive(id, isActive);

    }
}