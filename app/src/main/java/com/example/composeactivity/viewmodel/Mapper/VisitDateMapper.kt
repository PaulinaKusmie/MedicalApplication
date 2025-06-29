package com.example.composeactivity.viewmodel.Mapper

import com.example.composeactivity.compose.UIStateObject.VisitDateUiState
import com.example.composeactivity.data.entity.VisitDate

class VisitDateMapper {
    companion object {
        fun VisitDate.toUiState() = VisitDateUiState(
            id = id,
            specjalizationId = specjalizationId,
            examinationId = examinationId,
            doneDate = doneDate,
            predictedDate = predictedDate,
            appointmentDate = appointmentDate
        )

        fun VisitDateUiState.toEntity() = VisitDate(
            id = if (id.toLong() == -1L) 0 else id,
            specjalizationId = specjalizationId,
            examinationId = examinationId,
            doneDate = doneDate,
            predictedDate = predictedDate,
            appointmentDate = appointmentDate
        )

    }

}