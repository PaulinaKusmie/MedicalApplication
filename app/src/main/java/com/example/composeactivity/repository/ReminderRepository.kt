package com.example.composeactivity.repository

import com.example.composeactivity.compose.Tools.TimeType
import com.example.composeactivity.data.dao.ReminderDao
import com.example.composeactivity.data.entity.Reminder
import com.example.composeactivity.data.entity.Specjalization
import kotlinx.coroutines.flow.Flow

class ReminderRepository(private val dao: ReminderDao) {

    val allSpecjalization :Flow<List<Reminder>> = dao.getReminder();

    suspend fun updateActive(id : Int, countReminder: Int, typeOfTime: TimeType) = dao.updateReminder(id,countReminder,typeOfTime)

    suspend fun addSpecjalization(reminder: Reminder) = dao.insert(reminder)

    suspend fun deleteSpecjalization(reminder: Reminder) = dao.delete(reminder)

}