package com.example.composeactivity.repository

import com.example.composeactivity.api.ApiClient
import com.example.composeactivity.api.ReminderAPI
import com.example.composeactivity.compose.Tools.TimeType
import com.example.composeactivity.data.dao.ReminderDao
import com.example.composeactivity.data.entity.Reminder
import com.example.composeactivity.data.entity.Specjalization
import kotlinx.coroutines.flow.Flow

class ReminderRepository(private val reminderApi: ReminderAPI = ApiClient.reminderService) {

    suspend fun allReminder()  = reminderApi.getReminders(1);

//    suspend fun updateReminder(id : Int, countReminder: Int, typeOfTime: Int)
//    = reminderApi.updateReminder(id,countReminder,typeOfTime) do object

    suspend fun addReminder(reminder: Reminder) = reminderApi.addReminder(reminder)

    suspend fun deleteReminder(reminder: Reminder) = reminderApi.deleteReminder(reminder.id,1)

}