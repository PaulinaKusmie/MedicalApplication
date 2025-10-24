package com.example.composeactivity.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.composeactivity.data.entity.Specjalization
import com.example.composeactivity.repository.ReminderRepository
import com.example.composeactivity.repository.SpecjalizationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SpecjalizationViewModel @Inject constructor (private val repo: SpecjalizationRepository) : ViewModel() {

    val specjalizations = repo.allSpecjalizations()

         fun updateIsActive(id: Int, isActive: Boolean) =  viewModelScope.launch {
             try {
            //repo.updateActive(id, isActive)
             } catch (e : Exception){ Log.e("Error", "Fail updated active state specjalization") }

        }

        fun addSpecjalization(specjalization: Specjalization) = viewModelScope.launch{
            try {
            //repo.addSpecjalization(specjalization)
            } catch (e : Exception){ Log.e("Error", "Fail added specjalization") }
        }

        fun deleteSpecjalization(specjalization: Specjalization) = viewModelScope.launch{
            try {
            //repo.deleteSpecjalization(specjalization)
            } catch (e : Exception){ Log.e("Error", "Fail deleted specjalization") }
        }



}


