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

     fun  getAllExamination() :  Flow<List<Examination>>  = flow{ emit(examinationAPI.getAllExamination()) }

     fun  getExamination(userId: Int) :  Flow<List<Examination>>  = flow{ emit(examinationAPI.getExamination(userId)) }

    suspend fun addExamination(userid: Int, name: String) = examinationAPI.addExamination(userid,name)

    suspend fun deleteExamination(id: Int, userid: Int) = examinationAPI.deleteExamination(id,userid)

}