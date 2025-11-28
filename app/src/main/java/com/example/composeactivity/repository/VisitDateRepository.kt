package com.example.composeactivity.repository

import android.util.Log
import com.example.composeactivity.api.VisitDateAPI
import com.example.composeactivity.compose.Tools.DateType
import com.example.composeactivity.data.entity.VisitDate
import com.example.composeactivity.data.entity.VisitDateDTO
import javax.inject.Inject

class VisitDateRepository  @Inject constructor(private val visitDateAPI: VisitDateAPI) {

    suspend fun allVisitDate(userId : Int) = visitDateAPI.getVisitDates(userId);

    suspend fun getVisitDate(foreignId : Int,type :Int , userId : Int , ) : VisitDateDTO = visitDateAPI.getVisitDateByForeignIdAndType(foreignId, type, userId,  )

    suspend fun addVisit(visitDate: VisitDateDTO) = visitDateAPI.addVisit(visitDate)

    suspend fun updateDoneDate(visitDate: VisitDateDTO) = visitDateAPI.updateDoneDate(visitDate.id, visitDate.foreignId, visitDate.userId, visitDate)

    suspend fun updatePredictedDate(visitDate: VisitDateDTO) = visitDateAPI.updatePredictedDate(visitDate.id, visitDate.foreignId,  visitDate.userId, visitDate)

    suspend fun updateAppointmentDate(visitDate: VisitDateDTO) = visitDateAPI.updateAppointmentDate(visitDate.id, visitDate.foreignId,  visitDate.userId, visitDate)

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