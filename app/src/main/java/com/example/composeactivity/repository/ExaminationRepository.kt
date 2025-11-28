package com.example.composeactivity.repository

import com.example.composeactivity.api.ExaminationAPI
import com.example.composeactivity.data.entity.Examination
import com.example.composeactivity.data.entity.Reminder
import com.example.composeactivity.data.entity.VisitDate
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.UUID
import javax.inject.Inject

class ExaminationRepository  @Inject constructor (private val examinationAPI: ExaminationAPI) {

     fun  allExamination(userId: Int) :  Flow<List<Examination>>  = flow{ emit(examinationAPI.getExamination(userId)) }

    //suspend fun updateActive(id: Int, isActive: Boolean) = dao.updateActive(id,isActive)

    ///suspend fun addExamination(examination: Examination) = dao.insert(examination)

    //suspend fun deleteExamination(examination: Examination) = dao.delete(examination)

}