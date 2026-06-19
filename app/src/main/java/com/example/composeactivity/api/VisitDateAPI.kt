package com.example.composeactivity.api

import com.example.composeactivity.data.dto.ApiResponse
import com.example.composeactivity.data.entity.VisitDate
import com.example.composeactivity.data.entity.VisitDateDTO
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface VisitDateAPI {

    @GET("/visitDates/{userId}")
    suspend fun getVisitDates(@Path("userId") userId : Int) : List<VisitDate>

    @GET("/visitDates/{foreignId}/{type}/{userId}")
    suspend fun getVisitDateByForeignIdAndType(
        @Path("foreignId") foreignId : Int,
        @Path("type") type : Int,
        @Path("userId") userId : Int): VisitDateDTO

    @PUT("/visitDates")
    suspend fun addVisit(@Body visitDate: VisitDateDTO) : Response<ApiResponse>

    @PUT("/visitDates/doneDate/{id}/{foreignId}/{userId}")
    suspend fun updateDoneDate(
        @Path("id") id: Int,
        @Path("foreignId") foreignId: Int?,
        @Path("userId") userId: Int,
        @Body visitDate: VisitDateDTO
    ) : Response<Unit>

    @PUT("/visitDates/predictedDate/{id}/{foreignId}/{userId}")
    suspend fun updatePredictedDate(
        @Path("id") id: Int,
        @Path("foreignId") foreignId: Int?,
        @Path("userId") userId: Int,
        @Body visitDate: VisitDateDTO
    ) : Response<Unit>

    @PUT("/visitDates/appointmentDate/{id}/{foreignId}/{userId}")
    suspend fun updateAppointmentDate(
        @Path("id") id: Int,
        @Path("foreignId") foreignId: Int?,
        @Path("userId") userId: Int,
        @Body visitDate: VisitDateDTO
    ) : Response<Unit>


    @DELETE("/visitDates/doneDate/{id}/{userId}")
    suspend fun clearDoneDate(
        @Path("id") id : Int,
        @Path("userId") userId : Int
    ) : Response<Unit>

    @DELETE("/visitDates/predictedDate/{id}/{userId}")
    suspend fun clearPredictedDate(
        @Path("id") id : Int,
        @Path("userId") userId : Int
    ) : Response<Unit>

    @DELETE("/visitDates/appointmentDate/{id}/{userId}")
    suspend fun clearAppointmentDate(
        @Path("id") id : Int,
        @Path("userId") userId : Int
    ) : Response<Unit>

    @GET("/visitDates/predictedDates")
    suspend fun getVisitDateByDate(
        @Body  visitDate: VisitDate
    )
}
