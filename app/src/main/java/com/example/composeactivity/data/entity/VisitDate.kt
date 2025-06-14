package com.example.composeactivity.data.entity
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull


@Entity(tableName = "visitDate")
data class VisitDate (
    @PrimaryKey(autoGenerate = true) val id: Int,
    @NotNull val procedureId: Int,
    @NotNull val date: Long,
    val doneDate: Boolean,
    val predictedDate: Boolean,
    val appointmentDate: Boolean
)


