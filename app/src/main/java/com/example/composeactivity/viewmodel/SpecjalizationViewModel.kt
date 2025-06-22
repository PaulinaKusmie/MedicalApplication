package com.example.composeactivity.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope

import com.example.composeactivity.data.AppDatabase
import com.example.composeactivity.data.entity.Examination
import com.example.composeactivity.data.entity.Specjalization
import com.example.composeactivity.repository.SpecjalizationRepository
import kotlinx.coroutines.launch


class SpecjalizationViewModel(application: Application) : AndroidViewModel(application) {
    private val repo =  SpecjalizationRepository(AppDatabase.get(application).specjalizationDao())
    val specjalizations = repo.allSpecjalization.asLiveData()

         fun updateIsActive(id: Int, isActive: Boolean) =  viewModelScope.launch {
            repo.updateActive(id, isActive)
        }


        fun addSpecjalization(specjalization: Specjalization) = viewModelScope.launch{
            repo.addSpecjalization(specjalization)
        }

        fun deleteSpecjalization(specjalization: Specjalization) = viewModelScope.launch{
            repo.deleteSpecjalization(specjalization)
        }



}


