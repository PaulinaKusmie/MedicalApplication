package com.example.composeactivity.viewmodel

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope

import com.example.composeactivity.data.entity.Reminder
import com.example.composeactivity.data.entity.Specjalization
import com.example.composeactivity.repository.ReminderRepository
import com.example.composeactivity.utils.ToastManager

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.collections.find


class ReminderViewModel (application: Application,
                        private val repo: ReminderRepository) : AndroidViewModel(application) {
    private val _reminders = MutableStateFlow<List<Reminder>>(emptyList())
    val reminders: StateFlow<List<Reminder>> = _reminders

    init {
        fetchRemindersFromApi()
    }

    fun fetchRemindersFromApi() = viewModelScope.launch {
        val latest = repo.allReminder().first()
        _reminders.value = latest
    }

    fun addReminder() = viewModelScope.launch{
        try {

            val reminders = repo.allReminder().first()
            if(reminders.any { it.countReminder == 1 && it.typeOfTime == 0 })
            {
                ToastManager.showToast("You can't add same reminders")

            }else if (reminders.size > 4)
            {
                ToastManager.showToast("You have added maximum number of reminders")
            }
            else{
                val newReminder = Reminder(getLastId(),1,1,1) ///tu zmien user idddd
                repo.addReminder(newReminder)
            }

        } catch (e : Exception){ Log.e("Error", "Fail added reminder, please try again") }
    }


    fun updateReminder(reminder: Reminder) = viewModelScope.launch{
        try {
            val remindersCopy = repo.allReminder().first()
            if (remindersCopy.any { it.countReminder == reminder.countReminder && it.typeOfTime == reminder.typeOfTime })
            {
                ToastManager.showToast("You already have this same reminders")

            } else{
               if(reminder != null) {
                   repo.updateReminder(reminder.id, 1, reminder)
                   fetchRemindersFromApi()
               }
               else
                   ToastManager.showToast("Something went wrong!")



            }

        } catch (e : Exception){ Log.e("Error", "Fail updated reminder, please try again") }
    }

    fun deleteReminder(reminder: Reminder) = viewModelScope.launch{
        try {
            repo.deleteReminder(reminder)
        } catch (e : Exception){ Log.e("Error", "Fail deleted reminder, please try again") }
    }


    suspend fun getLastId() : Int {
        val reminders = repo.allReminder().first()
        val lasttId = if (reminders.isEmpty()) 1 else reminders.maxOf { it.id } + 1

        return lasttId
    }



}