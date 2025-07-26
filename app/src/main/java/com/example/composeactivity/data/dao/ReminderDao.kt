package com.example.composeactivity.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.composeactivity.compose.Tools.TimeType
import com.example.composeactivity.data.entity.Examination
import com.example.composeactivity.data.entity.Reminder
import kotlinx.coroutines.flow.Flow

@Dao
interface ReminderDao {

    @Query("SELECT * FROM  reminder")
    fun getReminder() : Flow<List<Reminder>>

    @Query("UPDATE reminder set countReminder = :countReminder, TypeOfTime = :typeOfTime where id = :id")
    suspend fun updateReminder( id : Int, countReminder: Int, typeOfTime: TimeType)

    @Insert()
    suspend fun insert(reminder: Reminder)

    @Delete()
    suspend fun delete(reminder: Reminder)

}