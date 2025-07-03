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
    fun getVisitDate(foreignId : Int, type :VisitType): VisitDate

    @Insert()
    suspend fun insert(visitDate: VisitDate)

    @Query("UPDATE visitDate set doneDate = :date and foreignId = :foreignId  where id = :id")
    suspend fun updateDoneDate(id: Int, date: Long?, foreignId: Int?)

    @Query("UPDATE visitDate set predictedDate = :date where id = :id")
    suspend fun updatePredictedDate( id : Int, date: Long)

    @Query("UPDATE visitDate set appointmentDate = :date where id = :id")
    suspend fun updateAppointmentDate( id : Int, date: Long)

}