package com.example.composeactivity.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.composeactivity.repository.ExaminationRepository
import kotlinx.coroutines.launch
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.composeactivity.data.entity.Examination
import com.example.composeactivity.data.entity.Reminder
import com.example.composeactivity.repository.ReminderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class ExaminationViewModel @Inject constructor (private val repo: ExaminationRepository) : ViewModel() {

    val examinations : Flow<List<Examination>> = repo.allExamination();

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