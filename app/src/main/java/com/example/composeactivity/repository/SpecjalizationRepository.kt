package com.example.composeactivity.repository

import com.example.composeactivity.api.SpecjalizationAPI

import com.example.composeactivity.data.entity.Specjalization
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.UUID
import javax.inject.Inject

class SpecjalizationRepository  @Inject constructor (private val specjalizationAPI: SpecjalizationAPI) {


     fun allSpecjalizations(userId: Int) : Flow<List<Specjalization>> =  flow{ emit(specjalizationAPI.getSpecjalizations(userId)) }

    //suspend fun updateActive(id: Int, isActive: Boolean) = dao.updateActive(id,isActive)

   // suspend fun addSpecjalization(specjalization: Specjalization) = dao.insert(specjalization)

   // suspend fun deleteSpecjalization(specjalization: Specjalization) = dao.delete(specjalization)

}