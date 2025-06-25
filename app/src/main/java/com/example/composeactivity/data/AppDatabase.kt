package com.example.composeactivity.data

import android.content.Context
import androidx.room.Database
import androidx.room.DatabaseConfiguration
import androidx.room.InvalidationTracker
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteOpenHelper
import com.example.composeactivity.data.dao.ExaminationDao
import com.example.composeactivity.data.dao.SpecjalizationDao
import com.example.composeactivity.data.dao.VisitDateDao
import com.example.composeactivity.data.entity.Examination
import com.example.composeactivity.data.entity.Specjalization
import com.example.composeactivity.data.entity.VisitDate

@Database(entities = [Examination::class, Specjalization::class , VisitDate::class], version = 3)
abstract class AppDatabase : RoomDatabase() {
    abstract  fun examinationDao(): ExaminationDao
    abstract  fun specjalizationDao(): SpecjalizationDao
    abstract  fun visitDateDao(): VisitDateDao



    companion object {
        @Volatile private  var INST: AppDatabase? = null
        fun get(context: Context): AppDatabase {
            return INST ?: synchronized(this) {
                INST ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "db_name"
                )
                    .fallbackToDestructiveMigration()
                    .build().also { INST = it }
            }
        }
    }

}