package com.example.composeactivity.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.composeactivity.data.entity.Specjalization
import com.example.composeactivity.data.entity.VisitDate
import kotlinx.coroutines.flow.Flow

@Dao
interface VisitDateDao {
    @Query("SELECT * FROM visitDate")
    fun getVisitDate(): Flow<List<VisitDate>>

    @Query("SELECT * FROM visitDate where (specjalizationId OR examinationId) = :id")
    fun getVisitDate(id : Int): VisitDate

    @Insert()
    suspend fun insert(visitDate: VisitDate)

    @Query("UPDATE visitDate set doneDate = :date where id = :id")
    suspend fun updateDoneDate( id : Int, date: Long)

    @Query("UPDATE visitDate set predictedDate = :date where id = :id")
    suspend fun updatePredictedDate( id : Int, date: Long)

    @Query("UPDATE visitDate set appointmentDate = :date where id = :id")
    suspend fun updateAppointmentDate( id : Int, date: Long)

}