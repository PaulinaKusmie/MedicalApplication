package com.example.composeactivity.api

import com.example.composeactivity.data.entity.Examination
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Path

interface ExaminationAPI {


    @GET("/examinations/{userId}")
    suspend fun getExamination(@Path("userId") userId: Int) : Flow<List<Examination>>
}