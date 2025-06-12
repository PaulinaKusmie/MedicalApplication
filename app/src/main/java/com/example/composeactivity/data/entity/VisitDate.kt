package com.example.composeactivity.data.entity
import android.provider.ContactsContract
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull
import java.time.LocalDateTime
import kotlin.uuid.ExperimentalUuidApi
import java.util.UUID


@Entity(tableName = "visitDate")
data class VisitDate (
    @PrimaryKey(autoGenerate = true) val id: Integer,
    @NotNull  val procedureId: Integer,
    @NotNull val date : Long ,
    val doneDate : Boolean,
    val predictedDate : Boolean,
    val appointmentDate : Boolean)


