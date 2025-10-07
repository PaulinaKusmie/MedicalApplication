package com.example.composeactivity.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.composeactivity.repository.ExaminationRepository
import kotlinx.coroutines.launch
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import com.example.composeactivity.data.entity.Examination
import com.example.composeactivity.data.entity.Reminder
import com.example.composeactivity.repository.ReminderRepository
import kotlinx.coroutines.flow.Flow


class ExaminationViewModel (application: Application,
                            private val repo: ExaminationRepository) : AndroidViewModel(application) {

    val examinations : Flow<List<Examination>> = repo.allExamination();

     fun updateIsActive(id: Int, isActive: Boolean) =  viewModelScope.launch {
         try {
        //repo.updateActive(id, isActive);
         } catch (e : Exception){ Log.e("Error", "Fail updated active state examination") }

    }

     fun addExamination(examination: Examination) = viewModelScope.launch{
         try {
       // repo.addExamination(examination)
         } catch (e : Exception){ Log.e("Error", "Fail added examination") }
    }

      fun deleteExamination(examination: Examination) = viewModelScope.launch{
          try {
       //repo.deleteExamination(examination)
      } catch (e : Exception){ Log.e("Error", "Fail deleted examination") }
    }
}