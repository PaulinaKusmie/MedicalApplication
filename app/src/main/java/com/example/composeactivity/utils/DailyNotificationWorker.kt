package com.example.composeactivity.utils


import android.content.Context
import android.icu.util.Calendar
import androidx.work.CoroutineWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.composeactivity.data.entity.VisitDate
import com.example.composeactivity.repository.VisitDateRepository
import com.example.composeactivity.viewmodel.Converter
import java.time.LocalDateTime

class DailyNotificationWorker(context : Context, params: WorkerParameters) :
    CoroutineWorker(context, params){

    override suspend fun doWork(): Result {
        ///// TU POPRAWWWW
       // private val repo: VisitDateRepository

        val startOfDay = Converter.localDateTimeToLong(LocalDateTime.now().toLocalDate().atStartOfDay())
        val endOfDay = Converter.localDateTimeToLong(LocalDateTime.now().toLocalDate().atTime(23, 59, 59))

        //var visitDate : List<Long> = repo.getVisitDateByDate(startOfDay, endOfDay)

//        visitDate.forEach {
//            NotificationUtils.scheduleNotification(applicationContext,it)
//        }

        return Result.success()
    }
}