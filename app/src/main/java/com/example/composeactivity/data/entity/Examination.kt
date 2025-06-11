package com.example.composeactivity.data.entity
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "examination")
data class Examination(
    @PrimaryKey val id: UUID,
    val name: String,
    val isActive: Boolean
)