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
import kotlinx.coroutines.launch

class ReminderViewModel(application: Application) : AndroidViewModel(application) {

    private val repo = ReminderRepository(AppDatabase.get(application).reminderDao())
    val reminders = repo.allReminder

//     fun Gogirsl(){
//        var rrr = Reminder(0, 1, 3)
//        var ddddd = Reminder(1, 2, 2)
//        addReminder(rrr)
//        addReminder(ddddd)
//    }




    fun addReminder(reminder: Reminder) = viewModelScope.launch{
        try {
            repo.addReminder(reminder)
        } catch (e : Exception){ Log.e("Error", "Fail added reminder") }
    }

}