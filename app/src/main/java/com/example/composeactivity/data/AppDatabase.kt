package com.example.composeactivity.data

import android.content.Context

import com.example.composeactivity.data.entity.Examination
import com.example.composeactivity.data.entity.Reminder
import com.example.composeactivity.data.entity.Specjalization
import com.example.composeactivity.data.entity.VisitDate

//@Database(entities = [Examination::class, Specjalization::class , VisitDate::class, Reminder::class ], version = 8)
//abstract class AppDatabase : RoomDatabase() {
//    abstract  fun examinationDao(): ExaminationDao
//    abstract  fun specjalizationDao(): SpecjalizationDao
//    abstract  fun visitDateDao(): VisitDateDao
//    abstract fun reminderDao(): ReminderDao


//    companion object {
//        @Volatile private  var INST: AppDatabase? = null
//        fun get(context: Context): AppDatabase {
//            return INST ?: synchronized(this) {
//                INST ?: Room.databaseBuilder(
//                    context.applicationContext,
//                    AppDatabase::class.java,
//                    "db_name"
//                )
//                    .fallbackToDestructiveMigration()
//                    .build().also { INST = it }
//            }
//        }
//    }

//}