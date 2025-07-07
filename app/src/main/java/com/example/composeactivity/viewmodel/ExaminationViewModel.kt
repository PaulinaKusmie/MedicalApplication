package com.example.composeactivity.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.composeactivity.data.AppDatabase
import com.example.composeactivity.repository.ExaminationRepository
import kotlinx.coroutines.launch
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import com.example.composeactivity.data.entity.Examination


class ExaminationViewModel (application: Application) : AndroidViewModel(application) {

    private val repo =  ExaminationRepository(AppDatabase.get(application).examinationDao())
    val examinations = repo.allExamination.asLiveData()

     fun updateIsActive(id: Int, isActive: Boolean) =  viewModelScope.launch {
         try {
        repo.updateActive(id, isActive);
         } catch (e : Exception){ Log.e("Error", "Fail updated active state examination") }

    }

     fun addExamination(examination: Examination) = viewModelScope.launch{
         try {
        repo.addExamination(examination)
         } catch (e : Exception){ Log.e("Error", "Fail added examination") }
    }

      fun deleteExamination(examination: Examination) = viewModelScope.launch{
          try {
        repo.deleteExamination(examination)
      } catch (e : Exception){ Log.e("Error", "Fail deleted examination") }
    }
}