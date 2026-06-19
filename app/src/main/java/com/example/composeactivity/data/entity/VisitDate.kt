package com.example.composeactivity.data.entity

import com.example.composeactivity.compose.Tools.VisitType
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


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


@Serializable data class VisitDateDTO (
    val id: Int,
    val userId: Int,
    val type: Int?,
    val foreignId: Int?,
    val doneDate: String?,
    val predictedDate: String?,
    val appointmentDate: String?,
    )