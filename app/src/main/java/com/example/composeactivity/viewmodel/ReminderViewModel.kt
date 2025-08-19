package com.example.composeactivity.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.composeactivity.data.AppDatabase
import com.example.composeactivity.data.entity.Reminder
import com.example.composeactivity.data.entity.Specjalization
import com.example.composeactivity.repository.ReminderRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class ReminderViewModel(application: Application) : AndroidViewModel(application) {

    private val repo = ReminderRepository(AppDatabase.get(application).reminderDao())
    val reminders = repo.allReminder.asLiveData()


//     fun Gogirsl(){
//        var rrr = Reminder(0, 1, 3)
//        var ddddd = Reminder(1, 2, 2)
//        addReminder(rrr)
//        addReminder(ddddd)
//    }


    fun addReminder() = viewModelScope.launch{
        try {
            val newReminder = Reminder(getLastId(),1,0)
            repo.addReminder(newReminder)
        } catch (e : Exception){ Log.e("Error", "Fail added reminder") }
    }



    fun updateReminder(id : Int, countReminder: Int, typeOfTime: Int) = viewModelScope.launch{
        try {
            repo.updateReminder(id, countReminder, typeOfTime)
        } catch (e : Exception){ Log.e("Error", "Fail updated reminder") }
    }


    suspend fun getLastId() : Int {
        val reminders = repo.allReminder.first()
        val lasttId = if (reminders.isEmpty()) 1 else reminders.maxOf { it.id } + 1

        return lasttId
    }



}