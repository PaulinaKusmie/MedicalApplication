package com.example.composeactivity.data.entity
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "specjalization")
data class Specjalization(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val isActive: Boolean,
    val sex: Int = 0, // 0=all, 1=women, 2=men
    val isPay: Boolean = false,
)

