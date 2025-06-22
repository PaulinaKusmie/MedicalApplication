package com.example.composeactivity.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.composeactivity.data.entity.Examination
import com.example.composeactivity.data.entity.Specjalization
import kotlinx.coroutines.flow.Flow
import java.util.UUID

@Dao
interface SpecjalizationDao {
    @Query("SELECT * FROM specjalization")
     fun getSpecjalization() : Flow<List<Specjalization>>

    @Query("UPDATE specjalization set IsActive = :isActive where id = :id")
    suspend fun updateActive( id : Int, isActive: Boolean)

    @Insert()
    suspend fun insert(specjalization: Specjalization)

    @Delete()
    suspend fun delete(specjalization: Specjalization)
}