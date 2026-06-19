package com.example.composeactivity.repository

import com.example.composeactivity.api.SpecjalizationUserAPI
import com.example.composeactivity.data.entity.SpecjalizationUser
import javax.inject.Inject

class SpecjalizationUserRepository  @Inject constructor (private val specjalizationUserAPI: SpecjalizationUserAPI ) {

     suspend fun addSpecjalizationUser(specjalizationId:Int, userId:Int)
     = specjalizationUserAPI.createSpecjalizationUser(specjalizationId, userId)

     suspend fun deleteSpecjalizationUser(specjalizationId:Int, userId:Int)
     = specjalizationUserAPI.deleteSpecjalizationUser(specjalizationId, userId)
}