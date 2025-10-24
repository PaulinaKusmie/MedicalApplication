package com.example.composeactivity.viewmodel

import android.app.Application
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composeactivity.data.entity.Reminder
import com.example.composeactivity.repository.ReminderRepository
import com.example.composeactivity.utils.ToastManager
import com.example.composeactivity.UserSession
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReminderViewModel @Inject constructor (private val repo: ReminderRepository)  : ViewModel() {


    private val _reminders = MutableStateFlow<List<Reminder>>(emptyList())
    val reminders: StateFlow<List<Reminder>> = _reminders

    private var userId: Int by mutableStateOf(0)

    init {
        fetchRemindersFromApi()
        fetchUserId()
    }

    fun fetchRemindersFromApi() = viewModelScope.launch {
        val latest = repo.allReminder().first()
        _reminders.value = latest
    }

    fun fetchUserId() = viewModelScope.launch {
        userId = UserSession.getUserIdOnce()!!
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
                val newReminder = Reminder(getLastId(),userId,1,1)
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
                   repo.updateReminder(reminder.id, userId, reminder)
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