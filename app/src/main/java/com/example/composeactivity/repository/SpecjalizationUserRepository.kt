package com.example.composeactivity.repository

import com.example.composeactivity.api.SpecjalizationUserAPI
import com.example.composeactivity.data.entity.SpecjalizationUser

class SpecjalizationUserRepository(private val specjalizationUserAPI: SpecjalizationUserAPI ) {

     suspend fun addSpecjalizationUser(specjalizationUser: SpecjalizationUser)
     = specjalizationUserAPI.createSpecjalizationUser(specjalizationUser)

     suspend fun deleteSpecjalizationUser(specjalizationUser: SpecjalizationUser)
     = specjalizationUserAPI.deleteSpecjalizationUser(specjalizationUser.id, specjalizationUser.userId)
}