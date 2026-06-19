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

    @PUT("/specjalizationUsers/{specjalizationId}/{userId}")
    suspend fun createSpecjalizationUser(
        @Path("specjalizationId") specjalizationId : Int,
        @Path("userId") userId : Int
    ) : Response<ApiResponse>

    @DELETE("/specjalizationUsers/{specjalizationId}/{userId}")
    suspend fun deleteSpecjalizationUser(
        @Path("specjalizationId") specjalizationId : Int,
        @Path("userId") userId : Int
    ) : Response<Unit>
}

