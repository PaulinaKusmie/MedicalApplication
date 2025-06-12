package com.example.composeactivity.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.composeactivity.data.AppDatabase
import com.example.composeactivity.repository.ExaminationRepository
import com.example.composeactivity.repository.VisitDateRepository
import kotlinx.coroutines.launch

class VisitDateViewModel (application: Application) : AndroidViewModel(application)  {

    private val repo =  VisitDateRepository(AppDatabase.get(application).visitDateDao())
    val examinations = repo.allVisitDate.asLiveData()

}