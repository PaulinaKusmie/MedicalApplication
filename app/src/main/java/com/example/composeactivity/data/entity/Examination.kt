package com.example.composeactivity.data.entity
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "examination")
data class Examination(
    @PrimaryKey val id: Int,
    val name: String,
    val isActive: Boolean,
    val ageFrom: Int? = null,
    val frequency: Int? = null, // year between examination
    val sex: Int = 0, // 0=all, 1=women, 2=men
    val isPay: Boolean = false,
    val isRefundable: Boolean = false,
    val description: String = ""
 )