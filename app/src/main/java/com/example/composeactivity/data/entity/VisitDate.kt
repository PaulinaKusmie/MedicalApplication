package com.example.composeactivity.data.entity
import android.provider.ContactsContract
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime
import kotlin.uuid.ExperimentalUuidApi
import java.util.UUID


@Entity(tableName = "visitDate")
data class VisitDate (
    @PrimaryKey(autoGenerate = true) val id: UUID,
    val procedureId: LocalDateTime,
    val doneDate : Boolean,
    val predictedDate : Boolean,
    val appointmentDate : Boolean)


