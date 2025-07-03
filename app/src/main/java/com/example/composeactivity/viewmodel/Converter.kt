package com.example.composeactivity.viewmodel

import java.time.Instant
import java.time.LocalDateTime
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
     }

 }