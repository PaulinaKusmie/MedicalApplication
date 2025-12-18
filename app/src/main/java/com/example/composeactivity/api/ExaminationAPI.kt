package com.example.composeactivity.api

import com.example.composeactivity.data.dto.ApiResponse
import com.example.composeactivity.data.entity.Examination
import com.example.composeactivity.data.entity.Reminder
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface ExaminationAPI {

    @GET("/examinations/")
    suspend fun getAllExamination() :List<Examination>

    @GET("/examinations/{userId}")
    suspend fun getExamination(@Path("userId") userId: Int) :List<Examination>

    @PUT("/examinations/{userId}/{name}")
    suspend fun addExamination(
        @Path("userId") id: Int,
        @Path("name") name: String
    ):  Response<ApiResponse>

    @DELETE("/examinations/{id}/{userId}")
    suspend fun deleteExamination(
        @Path("id") id: Int,
        @Path("userId") userId: Int
    ):  Response<Unit>
}