package com.example.composeactivity.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope

import com.example.composeactivity.data.AppDatabase
import com.example.composeactivity.repository.SpecjalizationRepository
import kotlinx.coroutines.launch


class SpecjalizationViewModel(application: Application) : AndroidViewModel(application) {
    private val repo =  SpecjalizationRepository(AppDatabase.get(application).specjalizationDao())
    val specjalizations = repo.allSpecjalization.asLiveData()

    fun updateIsActive(id: Int, isActive: Boolean) =  viewModelScope.launch {
            repo.updateActive(id, isActive);

    }

}


