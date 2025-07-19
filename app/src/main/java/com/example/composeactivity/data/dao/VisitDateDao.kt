package com.example.composeactivity.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.composeactivity.compose.VisitType
import com.example.composeactivity.data.entity.VisitDate
import kotlinx.coroutines.flow.Flow

@Dao
interface VisitDateDao {
    @Query("SELECT * FROM visitDate")
    fun getVisitDate(): Flow<List<VisitDate>>

    @Query("SELECT * FROM visitDate where foreignId = :foreignId and type =:type ")
    fun getVisitDate(foreignId: Int, type: VisitType): VisitDate

    @Insert()
    suspend fun insert(visitDate: VisitDate)

    @Query("UPDATE visitDate set doneDate = :date where id = :id and foreignId = :foreignId")
    suspend fun updateDoneDate(id: Int, date: Long?, foreignId: Int?)

    @Query("UPDATE visitDate set predictedDate = :date where id = :id and foreignId = :foreignId")
    suspend fun updatePredictedDate(id: Int, date: Long?, foreignId: Int?)

    @Query("UPDATE visitDate set appointmentDate = :date where id = :id and foreignId = :foreignId")
    suspend fun updateAppointmentDate(id: Int, date: Long?, foreignId: Int?)

    @Query("UPDATE visitDate set doneDate = NULL where id = :id")
    suspend fun clearDoneDate(id: Int)

    @Query("UPDATE visitDate set predictedDate = NULL where id = :id")
    suspend fun clearPredictedDate(id: Int)

    @Query("UPDATE visitDate set appointmentDate = NULL where id = :id")
    suspend fun clearAppointmentDate(id: Int)

    @Query("SELECT predictedDate FROM visitDate where predictedDate between :startPredictedDate and :endPredictedDate  and appointmentDate is NULL")
    fun getVisitDateByDate(startPredictedDate: Long, endPredictedDate: Long): List<Long>
}
