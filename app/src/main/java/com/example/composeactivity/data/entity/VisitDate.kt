package com.example.composeactivity.data.entity
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.composeactivity.compose.VisitType


@Entity(tableName = "visitDate")
data class VisitDate (
    @PrimaryKey(autoGenerate = true) val id: Int,
    val type: VisitType?,
    val foreignId: Int?,
    val doneDate: Long?,
    val predictedDate: Long?,
    val appointmentDate: Long?,
)


