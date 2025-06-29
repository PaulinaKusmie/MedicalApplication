package com.example.composeactivity.repository

import com.example.composeactivity.data.dao.VisitDateDao
import com.example.composeactivity.data.entity.VisitDate
import kotlinx.coroutines.flow.Flow

class VisitDateRepository(private val dao: VisitDateDao) {

    val allVisitDate :Flow<List<VisitDate>> = dao.getVisitDate();

    suspend fun getVisitDate(id: Int) = dao.getVisitDate(id)

    suspend fun addVisit(visitDate: VisitDate) = dao.insert(visitDate)

    suspend fun updateDoneDate( id : Int, date: Long) = dao.updateDoneDate(id,date)

    suspend fun updatePredictedDate( id : Int, date: Long) = dao.updatePredictedDate(id,date)

    suspend fun updateAppointmentDate( id : Int, date: Long) = dao.updateAppointmentDate(id,date)
}