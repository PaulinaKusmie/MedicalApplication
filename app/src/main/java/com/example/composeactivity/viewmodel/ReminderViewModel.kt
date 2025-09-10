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
import com.example.composeactivity.utils.ToastManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlin.collections.find

class ReminderViewModel(application: Application,
                        private val repo: ReminderRepository) : AndroidViewModel(application) {

    val reminders: Flow<List<Reminder>> = repo.allReminder()


    fun addReminder() = viewModelScope.launch{
        try {

            val reminders = repo.allReminder().first()
            if(reminders.any { it.countReminder == 1 && it.TypeOfTime == 0 })
            {
                ToastManager.showToast("You can't add same reminders")

            }else if (reminders.size > 4)
            {
                ToastManager.showToast("You have added maximum number of reminders")
            }
            else{
                val newReminder = Reminder(getLastId(),1,0)
                repo.addReminder(newReminder)
            }

        } catch (e : Exception){ Log.e("Error", "Fail added reminder, please try again") }
    }


    fun updateReminder(id : Int, countReminder: Int, typeOfTime: Int) = viewModelScope.launch{
        try {
            val reminders = repo.allReminder().first()
            if (reminders.any { it.countReminder == countReminder && it.TypeOfTime == typeOfTime })
            {
                ToastManager.showToast("You already have this same reminders")

            } else{
                var reminder : Reminder? = reminders.find { it.id == id }
                if(reminder != null){
                    repo.updateReminder(id, countReminder, reminder)
                }
                else{
                    ToastManager.showToast("Something went wrong!")
                }

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