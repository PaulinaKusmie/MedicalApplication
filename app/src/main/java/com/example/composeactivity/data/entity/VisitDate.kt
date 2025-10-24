package com.example.composeactivity.data.entity

import com.example.composeactivity.compose.Tools.VisitType
import kotlinx.serialization.Serializable


@Serializable
data class VisitDate (
    val id: Int,
    val userId: Int,
    val type: VisitType?,
    val foreignId: Int?,
    val doneDate: Long?,
    val predictedDate: Long?,
    val appointmentDate: Long?,
)


