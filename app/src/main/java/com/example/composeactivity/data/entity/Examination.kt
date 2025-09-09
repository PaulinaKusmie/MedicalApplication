package com.example.composeactivity.data.entity
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class Examination(
    val id: Int,
    val name: String,
    val isActive: Boolean,
    val ageFrom: Int,
    val frequency: Int, // year between examination
    val sex: Int, // 0=all, 1=women, 2=men
    val isPay: Boolean,
    val isRefundable: Boolean,
    val description: String,
 )