package com.example.composeactivity.api

import com.example.composeactivity.data.dto.ApiResponse
import com.example.composeactivity.data.entity.ExaminationUser
import com.example.composeactivity.data.entity.SpecjalizationUser
import com.example.composeactivity.data.entity.VisitDate
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.PUT
import retrofit2.http.Path

interface ExaminationUserAPI {

    @PUT("/examinationUsers/{examinationId}/{userId}")
    suspend fun createExaminationUser(
        @Path("examinationId") examinationId : Int,
        @Path("userId") userId : Int
    ) : Response<ApiResponse>

    @DELETE("/examinationUsers/{examinationId}/{userId}")
    suspend fun deleteExaminationUser(
        @Path("examinationId") examinationId : Int,
        @Path("userId") userId : Int
    ) : Response<Unit>
}