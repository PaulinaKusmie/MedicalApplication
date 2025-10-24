package com.example.composeactivity.repository

import com.example.composeactivity.api.VisitDateAPI
import com.example.composeactivity.compose.Tools.DateType
import com.example.composeactivity.data.entity.VisitDate
import javax.inject.Inject

class VisitDateRepository  @Inject constructor(private val visitDateAPI: VisitDateAPI) {

    suspend fun allVisitDate() = visitDateAPI.getVisitDates(1);

    suspend fun getVisitDate(foreignId : Int, userId : Int , type :Int ) = visitDateAPI.getVisitDateByForeignIdAndType(foreignId, userId,  type)

    suspend fun addVisit(visitDate: VisitDate) = visitDateAPI.addVisit(visitDate)

    suspend fun updateDoneDate(visitDate: VisitDate) = visitDateAPI.updateDoneDate(visitDate.id, visitDate.foreignId, visitDate.userId, visitDate)

    suspend fun updatePredictedDate(visitDate: VisitDate) = visitDateAPI.updatePredictedDate(visitDate.id, visitDate.foreignId,  visitDate.userId, visitDate)

    suspend fun updateAppointmentDate(visitDate: VisitDate) = visitDateAPI.updateAppointmentDate(visitDate.id, visitDate.foreignId,  visitDate.userId, visitDate)

    suspend fun clearDate(id: Int, userId: Int, type: DateType){
        when(type) {
            DateType.DONE -> {
                visitDateAPI.clearDoneDate(id,userId)
            }
            DateType.PREDICTED -> {
                visitDateAPI.clearPredictedDate(id,userId)
            }
            DateType.APPOITMENT -> {
                visitDateAPI.clearAppointmentDate(id,userId)
            }
        }
    }

   // suspend fun getVisitDateByDate(startPredictedDate: Long, endPredictedDate: Long) = visitDateAPI.getVisitDateByDate(startPredictedDate,endPredictedDate )
}