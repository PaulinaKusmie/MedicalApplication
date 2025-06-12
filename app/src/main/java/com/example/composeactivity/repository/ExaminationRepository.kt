package com.example.composeactivity.repository

import com.example.composeactivity.data.dao.ExaminationDao
import com.example.composeactivity.data.entity.Examination
import kotlinx.coroutines.flow.Flow
import java.util.UUID

class ExaminationRepository(private val dao: ExaminationDao) {

    val allExamination :Flow<List<Examination>> = dao.getExamination();

    suspend fun updateActive(id: Int, isActive: Boolean) = dao.updateActive(id,isActive)
}