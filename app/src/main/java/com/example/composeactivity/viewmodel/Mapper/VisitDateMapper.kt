package com.example.composeactivity.viewmodel.Mapper

import com.example.composeactivity.compose.Tools.VisitType
import com.example.composeactivity.compose.UIStateObject.VisitDateUiState
import com.example.composeactivity.data.entity.VisitDate
import com.example.composeactivity.data.entity.VisitDateDTO
import com.example.composeactivity.viewmodel.Converter.Companion.toEpochMillisUtc
import com.example.composeactivity.viewmodel.Converter.Companion.toLocalDateTimeUtc
import com.example.composeactivity.viewmodel.Converter


class VisitDateMapper {
    companion object {
        fun VisitDate.toUiState() = VisitDateUiState(
            id = id,
            userId = userId,
            type = type,
            foreignId = foreignId,
            doneDate = doneDate,
            predictedDate = predictedDate,
            appointmentDate = appointmentDate
        )

        fun VisitDateUiState.toEntity() = VisitDate(
            id = if (id.toLong() == -1L) 0 else id,
            userId = userId,
            type = type,
            foreignId = foreignId,
            doneDate = doneDate,
            predictedDate = predictedDate,
            appointmentDate = appointmentDate
        )

        fun VisitDate.toDTO(): VisitDateDTO =
            VisitDateDTO(
                id = id,
                userId = userId,
                type = if (type == VisitType.SPECIALIZATION) 0 else 1,
                foreignId = foreignId,
                doneDate = if(doneDate != null) doneDate.toLocalDateTimeUtc().toString() else null,
                predictedDate = if(predictedDate != null) predictedDate.toLocalDateTimeUtc().toString() else null,
                appointmentDate = if(appointmentDate != null) appointmentDate.toLocalDateTimeUtc().toString() else null,
            )

        fun VisitDateDTO.toDomain(): VisitDate =
            VisitDate(
                id = id,
                userId = userId,
                type = if (type == 0) VisitType.SPECIALIZATION else VisitType.EXAMINATION,
                foreignId = foreignId,
                doneDate = Converter.stringToLong(doneDate.toString()),
                predictedDate = Converter.stringToLong(predictedDate.toString()),
                appointmentDate = Converter.stringToLong(appointmentDate.toString())
            )

    }

}