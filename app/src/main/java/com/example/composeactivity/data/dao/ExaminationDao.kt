package com.example.composeactivity.data.dao
import androidx.room.Dao
import androidx.room.Query
import com.example.composeactivity.data.entity.Examination
import kotlinx.coroutines.flow.Flow
import java.util.UUID

@Dao
interface ExaminationDao {

    @Query("SELECT * FROM examination")
    fun getExamination() : Flow<List<Examination>>

    @Query("UPDATE examination set IsActive = :isActive where id = :id")
    fun updateActive( id : Int, isActive: Boolean)

}
