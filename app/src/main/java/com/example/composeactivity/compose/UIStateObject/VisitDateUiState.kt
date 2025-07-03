package com.example.composeactivity.compose.UIStateObject

import com.example.composeactivity.compose.VisitType
import com.example.composeactivity.data.entity.VisitDate

data class VisitDateUiState(
    val id: Int = -1,
    val type: VisitType? = null,
    val foreignId: Int? = null,
    var name : String?  = null,
    var doneDate: Long? = null,
    var predictedDate: Long? = null,
    var appointmentDate: Long? = null
)

