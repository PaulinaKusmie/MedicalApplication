package com.example.composeactivity.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.composeactivity.compose.Tools.TimeType
import kotlinx.serialization.Serializable

@Serializable
data class Reminder(
    val id: Int,
    var countReminder: Int,
    var TypeOfTime: Int,)


