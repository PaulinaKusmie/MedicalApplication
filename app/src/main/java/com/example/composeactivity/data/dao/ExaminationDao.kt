package com.example.composeactivity.data.dao
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.composeactivity.data.entity.Examination
import com.example.composeactivity.data.entity.VisitDate

import kotlinx.coroutines.flow.Flow
import java.util.UUID

@Dao
interface ExaminationDao {

    @Query("SELECT * FROM examination")
    fun getExamination() : Flow<List<Examination>>

    @Query("UPDATE examination set IsActive = :isActive where id = :id")
    suspend fun updateActive( id : Int, isActive: Boolean)

    @Insert()
    suspend fun insert(examination: Examination)

    @Delete()
    suspend fun delete(examination: Examination)

    @Query("SELECT name FROM examination where id = :id ")
    fun getExaminationName(id : Int): String

}
