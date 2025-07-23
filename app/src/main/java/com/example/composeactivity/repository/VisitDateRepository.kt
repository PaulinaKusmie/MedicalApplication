package com.example.composeactivity.repository

import com.example.composeactivity.compose.Tools.DateType
import com.example.composeactivity.compose.Tools.VisitType
import com.example.composeactivity.data.dao.VisitDateDao
import com.example.composeactivity.data.entity.VisitDate
import kotlinx.coroutines.flow.Flow

class VisitDateRepository(private val dao: VisitDateDao) {

    val allVisitDate :Flow<List<VisitDate>> = dao.getVisitDate();

    suspend fun getVisitDate(foreignId : Int, type :VisitType ) = dao.getVisitDate(foreignId, type)

    suspend fun addVisit(visitDate: VisitDate) = dao.insert(visitDate)

    suspend fun updateDoneDate(visitDate: VisitDate) = dao.updateDoneDate(visitDate.id, visitDate.doneDate,  visitDate.foreignId)

    suspend fun updatePredictedDate(visitDate: VisitDate) = dao.updatePredictedDate(visitDate.id, visitDate.predictedDate,  visitDate.foreignId)

    suspend fun updateAppointmentDate(visitDate: VisitDate) = dao.updateAppointmentDate(visitDate.id, visitDate.appointmentDate,  visitDate.foreignId)

    suspend fun clearDate(id: Int, type:DateType){
        when(type) {
            DateType.DONE -> {
                dao.clearDoneDate(id)
            }
            DateType.PREDICTED -> {
                dao.clearPredictedDate(id)
            }
            DateType.APPOITMENT -> {
                dao.clearAppointmentDate(id)
            }
        }
    }

    suspend fun getVisitDateByDate(startPredictedDate: Long, endPredictedDate: Long) = dao.getVisitDateByDate(startPredictedDate,endPredictedDate )
}