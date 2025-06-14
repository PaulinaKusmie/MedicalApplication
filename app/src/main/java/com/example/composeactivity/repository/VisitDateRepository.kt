package com.example.composeactivity.repository

import com.example.composeactivity.data.dao.VisitDateDao
import com.example.composeactivity.data.entity.VisitDate
import kotlinx.coroutines.flow.Flow

class VisitDateRepository(private val dao: VisitDateDao) {

    val allVisitDate :Flow<List<VisitDate>> = dao.getVisitDate();

    suspend fun addVisit(visitDate: VisitDate) = dao.insert(visitDate)
}