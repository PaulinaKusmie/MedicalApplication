package com.example.composeactivity.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope

import com.example.composeactivity.data.AppDatabase

import com.example.composeactivity.data.entity.Specjalization
import com.example.composeactivity.repository.SpecjalizationRepository
import kotlinx.coroutines.launch


class SpecjalizationViewModel(application: Application) : AndroidViewModel(application) {

    private val repo =  SpecjalizationRepository(AppDatabase.get(application).specjalizationDao())
    val specjalizations = repo.allSpecjalization.asLiveData()

         fun updateIsActive(id: Int, isActive: Boolean) =  viewModelScope.launch {
             try {
            repo.updateActive(id, isActive)
             } catch (e : Exception){ Log.e("Error", "Fail updated active state specjalization") }

        }




        fun addSpecjalization(specjalization: Specjalization) = viewModelScope.launch{
            try {
            repo.addSpecjalization(specjalization)
            } catch (e : Exception){ Log.e("Error", "Fail added specjalization") }
        }

        fun deleteSpecjalization(specjalization: Specjalization) = viewModelScope.launch{
            try {
            repo.deleteSpecjalization(specjalization)
            } catch (e : Exception){ Log.e("Error", "Fail deleted specjalization") }
        }



}


