package com.example.composeactivity.api

import com.example.composeactivity.data.dto.ApiResponse
import com.example.composeactivity.data.entity.VisitDate
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.PUT
import retrofit2.http.Path

interface ExaminationUserAPI {

    @PUT("/visitDates")
    suspend fun addVisit(@Body visitDate: VisitDate) : Response<ApiResponse>

    @DELETE("/visitDates/{id}/{userId}")
    suspend fun clearAppointmentDate(
        @Path("id") id : Int,
        @Path("userId") userId : Int
    ) : Response<Unit>
}