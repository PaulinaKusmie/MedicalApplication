package com.example.composeactivity.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.composeactivity.data.entity.Examination
import kotlinx.coroutines.flow.Flow

@Dao
interface ConfigurationDao {

    @Query("SELECT * FROM  configuration")
    fun getExamination() : Flow<List<Examination>>

}