package com.example.composeactivity.repository

import com.example.composeactivity.api.ExaminationUserAPI
import com.example.composeactivity.data.entity.ExaminationUser
import com.example.composeactivity.data.entity.SpecjalizationUser
import javax.inject.Inject

class ExaminationUserRepository  @Inject constructor (private val examinationUserAPI: ExaminationUserAPI) {

    suspend fun addExaminationUser(examinationId:Int, userId:Int)
            = examinationUserAPI.createExaminationUser(examinationId, userId)

    suspend fun deleteExaminationUser(examinationId:Int, userId:Int)
            = examinationUserAPI.deleteExaminationUser(examinationId, userId)

}