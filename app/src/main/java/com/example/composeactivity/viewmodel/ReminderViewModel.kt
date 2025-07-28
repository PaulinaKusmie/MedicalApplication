package com.example.composeactivity.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import com.example.composeactivity.data.AppDatabase
import com.example.composeactivity.repository.ReminderRepository

class ReminderViewModel(application: Application) : AndroidViewModel(application) {

    private val repo = ReminderRepository(AppDatabase.get(application).reminderDao())
    //val reminders = repo.allReminder.asLiveData()

}