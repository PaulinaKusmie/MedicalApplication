package com.example.composeactivity.api

import com.example.composeactivity.data.dto.ApiResponse
import com.example.composeactivity.data.entity.SpecjalizationUser
import com.example.composeactivity.data.entity.VisitDate
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.PUT
import retrofit2.http.Path

interface SpecjalizationUserAPI {

    @PUT("/specjalizationUsers")
    suspend fun createSpecjalizationUser(@Body specjalizationUser: SpecjalizationUser) : Response<ApiResponse>

    @DELETE("/specjalizationUsers/{id}/{userId}")
    suspend fun deleteUserExamination(
        @Path("id") id : Int,
        @Path("userId") userId : Int
    ) : ApiResponse
}

