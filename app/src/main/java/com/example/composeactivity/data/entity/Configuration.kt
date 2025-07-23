package com.example.composeactivity.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "configuration")
data class Configuration(
    @PrimaryKey val id: Int,
    val countReminder: String,
    val isActive: Boolean,
    val ageFrom: Int? = null)


