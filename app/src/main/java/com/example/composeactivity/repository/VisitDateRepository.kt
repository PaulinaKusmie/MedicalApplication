package com.example.composeactivity.repository

import com.example.composeactivity.api.VisitDateAPI
import com.example.composeactivity.compose.Tools.DateType
import com.example.composeactivity.data.entity.VisitDate

class VisitDateRepository(private val visitDateAPI: VisitDateAPI) {

    suspend fun allVisitDate() = visitDateAPI.getVisitDates(1);

    suspend fun getVisitDate(foreignId : Int, type :Int ) = visitDateAPI.getVisitDateByForeignIdAndType(foreignId, 1,  type)

    suspend fun addVisit(visitDate: VisitDate) = visitDateAPI.addVisit(visitDate)

    suspend fun updateDoneDate(visitDate: VisitDate) = visitDateAPI.updateDoneDate(visitDate.id, visitDate.foreignId, 1, visitDate)

    suspend fun updatePredictedDate(visitDate: VisitDate) = visitDateAPI.updatePredictedDate(visitDate.id, visitDate.foreignId,  1, visitDate)

    suspend fun updateAppointmentDate(visitDate: VisitDate) = visitDateAPI.updateAppointmentDate(visitDate.id, visitDate.foreignId,  1, visitDate)

    suspend fun clearDate(id: Int, type:DateType){
        when(type) {
            DateType.DONE -> {
                visitDateAPI.clearDoneDate(id,1)
            }
            DateType.PREDICTED -> {
                visitDateAPI.clearPredictedDate(id,1)
            }
            DateType.APPOITMENT -> {
                visitDateAPI.clearAppointmentDate(id,1)
            }
        }
    }

   // suspend fun getVisitDateByDate(startPredictedDate: Long, endPredictedDate: Long) = visitDateAPI.getVisitDateByDate(startPredictedDate,endPredictedDate )
}