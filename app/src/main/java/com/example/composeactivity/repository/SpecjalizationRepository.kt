package com.example.composeactivity.repository

import android.util.Log
import com.example.composeactivity.api.SpecjalizationAPI

import com.example.composeactivity.data.entity.Specjalization
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.UUID
import javax.inject.Inject

class SpecjalizationRepository  @Inject constructor (private val specjalizationAPI: SpecjalizationAPI) {

     fun getAllSpecjalizations(): Flow<List<Specjalization>> = flow { emit(specjalizationAPI.getAllSpecjalizations()) }

     fun getSpecjalizations(userId: Int): Flow<List<Specjalization>> = flow { emit(specjalizationAPI.getSpecjalizations(userId)) }

     suspend fun addSpecjalization(userid: Int, name: String) = specjalizationAPI.addSpecjalization(userid,name)

     suspend fun deleteSpecjalization(id: Int, userid: Int) = specjalizationAPI.deleteSpecjalization(id,userid)


}