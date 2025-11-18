package com.example.composeactivity.compose.UIStateObject

import com.example.composeactivity.compose.Tools.VisitType

data class VisitDateUiState(
    val id: Int = -1,
    val  userId : Int = -1,
    var type: VisitType? = null,
    val foreignId: Int? = null,
    var name : String?  = null,
    var doneDate: Long? = null,
    var predictedDate: Long? = null,
    var appointmentDate: Long? = null
)

