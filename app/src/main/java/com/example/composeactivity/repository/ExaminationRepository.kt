package com.example.composeactivity.repository

import com.example.composeactivity.api.ExaminationAPI
import com.example.composeactivity.data.dao.ExaminationDao
import com.example.composeactivity.data.entity.Examination
import com.example.composeactivity.data.entity.VisitDate
import kotlinx.coroutines.flow.Flow
import java.util.UUID

class ExaminationRepository(private val examinationAPI: ExaminationAPI) {

    suspend fun  allExamination()  = examinationAPI.getExamination(1);

    //suspend fun updateActive(id: Int, isActive: Boolean) = dao.updateActive(id,isActive)

    ///suspend fun addExamination(examination: Examination) = dao.insert(examination)

    //suspend fun deleteExamination(examination: Examination) = dao.delete(examination)

}