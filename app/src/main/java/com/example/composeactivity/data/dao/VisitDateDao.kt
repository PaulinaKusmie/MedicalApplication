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

    @Insert()
    suspend fun insert(visitDate: VisitDate)

}