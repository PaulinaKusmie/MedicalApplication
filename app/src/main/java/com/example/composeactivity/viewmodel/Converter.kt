package com.example.composeactivity.viewmodel

import com.example.composeactivity.compose.Tools.VisitType
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.time.Instant
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.ZoneId
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

         fun localDateTimeToFormattedString(dateTime: LocalDateTime): String {
             val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")
             return dateTime.format(formatter)
         }

         fun stringToLocalDateTime(dateString: String): LocalDateTime {
             // jeśli string ma "Z" na końcu (UTC)
             return OffsetDateTime.parse(dateString).toLocalDateTime()
         }


         fun localDateTimeToString(localDateTime: LocalDateTime): String {
             val offsetDateTime = localDateTime.atOffset(java.time.ZoneOffset.UTC)
             return offsetDateTime.toString() // np. "2025-10-22T21:45:00Z"
         }
     }

 }




