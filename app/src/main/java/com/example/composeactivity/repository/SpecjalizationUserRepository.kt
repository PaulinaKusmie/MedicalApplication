package com.example.composeactivity.repository

import com.example.composeactivity.api.SpecjalizationUserAPI
import com.example.composeactivity.data.entity.SpecjalizationUser

class SpecjalizationUserRepository(private val specjalizationUserAPI: SpecjalizationUserAPI ) {

     suspend fun addSpecjalizationUser(specjalization: SpecjalizationUser) = specjalizationUserAPI.createSpecjalizationUser(specjalization)

     suspend fun deleteSpecjalizationUser(specjalization: SpecjalizationUser) = specjalizationUserAPI.deleteUserExamination()
}