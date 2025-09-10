package com.example.composeactivity.repository

import com.example.composeactivity.api.ApiClient
import com.example.composeactivity.api.ReminderAPI
import com.example.composeactivity.compose.Tools.TimeType
import com.example.composeactivity.data.dao.ReminderDao
import com.example.composeactivity.data.entity.Reminder
import com.example.composeactivity.data.entity.Specjalization
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ReminderRepository(private val reminderApi: ReminderAPI = ApiClient.reminderService) {

     fun allReminder(): Flow<List<Reminder>> = flow{
            emit(reminderApi.getReminders(1))
        };

    suspend fun updateReminder(userId : Int, id: Int, reminder: Reminder)
    = reminderApi.updateReminder(userId, id, reminder)

    suspend fun addReminder(reminder: Reminder) = reminderApi.addReminder(reminder)

    suspend fun deleteReminder(reminder: Reminder) = reminderApi.deleteReminder(reminder.id,1)

}