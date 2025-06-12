package com.example.composeactivity.repository

import com.example.composeactivity.data.dao.SpecjalizationDao
import com.example.composeactivity.data.entity.Specjalization
import kotlinx.coroutines.flow.Flow
import java.util.UUID

class SpecjalizationRepository(private val dao: SpecjalizationDao) {

    val allSpecjalization :Flow<List<Specjalization>> = dao.getSpecjalization();

    suspend fun updateActive(id: Int, isActive: Boolean) = dao.updateActive(id,isActive)

}