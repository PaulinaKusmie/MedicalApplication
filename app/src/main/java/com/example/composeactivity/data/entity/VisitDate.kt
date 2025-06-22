package com.example.composeactivity.data.entity
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "visitDate")
data class VisitDate (
    @PrimaryKey(autoGenerate = true) val id: Int,
    val specjalizationId: Int?,
    val examinationId: Int?,
    val doneDate: Long?,
    val predictedDate: Long?,
    val appointmentDate: Long?,
)


