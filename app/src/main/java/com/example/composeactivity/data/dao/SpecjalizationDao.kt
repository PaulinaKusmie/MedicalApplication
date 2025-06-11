package com.example.composeactivity.data.dao

import androidx.room.Query
import com.example.composeactivity.data.entity.Examination
import com.example.composeactivity.data.entity.Specjalization
import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface SpecjalizationDao {
    @Query("SELECT * FROM specjalization")
    fun getSpecjalization() : Flow<List<Specjalization>>

    @Query("UPDATE specjalization set IsActive = :isActive where id = :id")
    fun updateActive( id : UUID, isActive: Boolean)
}