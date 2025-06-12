package com.example.composeactivity.data.entity
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID


@Entity(tableName = "specjalization")
data class Specjalization(
    @PrimaryKey(autoGenerate = true) val id: Integer,
    val name: String,
    val isActive: Boolean
)

