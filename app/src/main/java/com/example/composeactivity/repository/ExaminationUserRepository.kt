package com.example.composeactivity.repository

import com.example.composeactivity.api.ExaminationUserAPI
import com.example.composeactivity.data.entity.ExaminationUser
import com.example.composeactivity.data.entity.SpecjalizationUser
import javax.inject.Inject

class ExaminationUserRepository  @Inject constructor (private val examinationUserAPI: ExaminationUserAPI) {

    suspend fun addExaminationUser(examinationUser: ExaminationUser)
            = examinationUserAPI.createExaminationUser(examinationUser)

    suspend fun deleteExaminationUser(examinationUser: ExaminationUser)
            = examinationUserAPI.deleteExaminationUser(examinationUser.id, examinationUser.userId)

}