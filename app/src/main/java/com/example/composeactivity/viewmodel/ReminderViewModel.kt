package com.example.composeactivity.viewmodel

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.composeactivity.data.AppDatabase
import com.example.composeactivity.data.entity.Reminder
import com.example.composeactivity.data.entity.Specjalization
import com.example.composeactivity.repository.ReminderRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class ReminderViewModel(application: Application) : AndroidViewModel(application) {

    private val repo = ReminderRepository(AppDatabase.get(application).reminderDao())
    val reminders = repo.allReminder.asLiveData()

    private val _toastEvent = MutableSharedFlow<String>()
    val toastEvent = _toastEvent

    fun showToast(message: String) {
        viewModelScope.launch {
            _toastEvent.emit(message)
        }
    }


    fun addReminder() = viewModelScope.launch{
        try {

            val reminders = repo.allReminder.first()


            var dupa = reminders.find  {x -> x.countReminder == 1 && x.TypeOfTime == 0 }

            if(dupa != null)
            {
                _toastEvent.emit("You can't add either same reminders")

            } else{
                val newReminder = Reminder(getLastId(),1,0)
                repo.addReminder(newReminder)
            }

        } catch (e : Exception){ Log.e("Error", "Fail added reminder") }
    }


    fun updateReminder(id : Int, countReminder: Int, typeOfTime: Int) = viewModelScope.launch{
        try {
            repo.updateReminder(id, countReminder, typeOfTime)
        } catch (e : Exception){ Log.e("Error", "Fail updated reminder") }
    }

    fun deleteReminder(reminder: Reminder) = viewModelScope.launch{
        try {
            repo.deleteReminder(reminder)
        } catch (e : Exception){ Log.e("Error", "Fail updated reminder") }
    }


    suspend fun getLastId() : Int {
        val reminders = repo.allReminder.first()
        val lasttId = if (reminders.isEmpty()) 1 else reminders.maxOf { it.id } + 1

        return lasttId
    }



}