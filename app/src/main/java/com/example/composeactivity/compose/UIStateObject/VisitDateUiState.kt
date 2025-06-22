package com.example.composeactivity.compose.UIStateObject

import com.example.composeactivity.data.entity.VisitDate

data class VisitDateUiState(
    val id: Int = -1,
    val specjalizationId: Int? = null,
    val examinationId: Int? = null,
    val doneDate: Long? = null,
    val predictedDate: Long? = null,
    val appointmentDate: Long? = null
)

