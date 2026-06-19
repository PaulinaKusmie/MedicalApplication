package com.example.composeactivity.viewmodel

import android.R.string
import android.util.Log
import com.example.composeactivity.compose.Tools.VisitType
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.time.Instant
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

class  Converter {
     companion object {
         fun longToLocalDateTime(timestamp: Long?): LocalDateTime? {
             return timestamp?.let {
                 Instant.ofEpochMilli(it)
                     .atZone(ZoneId.systemDefault())
                     .toLocalDateTime()
             }
         }

         fun localDateTimeToLong(dateTime: LocalDateTime): Long {
             return dateTime.atZone(ZoneId.systemDefault())
                 .toInstant()
                 .toEpochMilli()
         }

         fun longToFormattedDateTime(millis: Long): String {
             val dateTime = Instant.ofEpochMilli(millis)
                 .atZone(ZoneId.systemDefault())
                 .toLocalDateTime()
             val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")
             return dateTime.format(formatter)
         }

         fun stringToLong(dateString: String?): Long? {
             if (dateString == "null") return null
             else {
                 return try {
                     val instant = Instant.parse(dateString)
                     val trimmed = LocalDateTime
                         .ofInstant(instant, ZoneOffset.UTC)
                         .withSecond(0)
                         .withNano(0)
                     trimmed.toInstant(ZoneOffset.UTC).toEpochMilli()
                 } catch (e: Exception) {
                     null
                 }
             }

         }



         fun Long.toLocalDateTimeUtc(): LocalDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(this), ZoneOffset.systemDefault())
         fun LocalDateTime.toEpochMillisUtc(): Long = this.toInstant(ZoneOffset.UTC).toEpochMilli()

     }

 }




