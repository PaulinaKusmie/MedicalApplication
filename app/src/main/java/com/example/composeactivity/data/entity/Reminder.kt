package com.example.composeactivity.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.composeactivity.compose.Tools.TimeType

@Entity(tableName = "reminder")
data class Reminder(
    @PrimaryKey val id: Int,
    var countReminder: Int = 0,
    var TypeOfTime: Int = 0)


