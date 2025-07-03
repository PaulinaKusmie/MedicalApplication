package com.example.composeactivity.repository

import com.example.composeactivity.compose.VisitType
import com.example.composeactivity.data.dao.VisitDateDao
import com.example.composeactivity.data.entity.VisitDate
import kotlinx.coroutines.flow.Flow

class VisitDateRepository(private val dao: VisitDateDao) {

    val allVisitDate :Flow<List<VisitDate>> = dao.getVisitDate();

    suspend fun getVisitDate(foreignId : Int, type :VisitType ) = dao.getVisitDate(foreignId, type)

    suspend fun addVisit(visitDate: VisitDate) = dao.insert(visitDate)

    suspend fun updateDoneDate(visitDate: VisitDate) = dao.updateDoneDate(visitDate.id, visitDate.doneDate,  visitDate.foreignId)

    suspend fun updatePredictedDate( id : Int, date: Long) = dao.updatePredictedDate(id,date)

    suspend fun updateAppointmentDate( id : Int, date: Long) = dao.updateAppointmentDate(id,date)
}