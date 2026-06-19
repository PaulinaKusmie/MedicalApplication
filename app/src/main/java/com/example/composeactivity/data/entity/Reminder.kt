package com.example.composeactivity.data.entity


import com.example.composeactivity.compose.Tools.TimeType
import kotlinx.serialization.Serializable

@Serializable
data class Reminder(
    val id: Int,
    val userId: Int,
    var countReminder: Int,
    var typeOfTime: Int)


