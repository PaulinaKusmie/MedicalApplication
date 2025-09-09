package com.example.composeactivity.api

import com.example.composeactivity.data.dto.ApiResponse
import com.example.composeactivity.data.entity.VisitDate
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

    @GET("/visitDates/{userId}/{foreignId}/{type}")
    suspend fun getVisitDateByForeignIdAndType(
        @Path("userId") userId : Int,
        @Path("foreignId") foreignId : Int,
        @Path("type") type : Int) : VisitDate

    @PUT("/visitDates")
    suspend fun addVisit(@Body visitDate: VisitDate) : Response<ApiResponse>

    @POST("/visitDates/{id}/{foreignId}/{userId}")
    suspend fun updateDoneDate(
        @Path("id") id: Int,
        @Path("foreignId") foreignId: Int?,
        @Path("userId") userId: Int,
        @Body visitDate: VisitDate
    ) : Response<Unit>

    @POST("/visitDates/{id}/{foreignId}/{userId}")
    suspend fun updatePredictedDate(
        @Path("id") id: Int,
        @Path("foreignId") foreignId: Int?,
        @Path("userId") userId: Int,
        @Body visitDate: VisitDate
    ) : Response<Unit>

    @POST("/visitDates/{id}/{foreignId}/{userId}")
    suspend fun updateAppointmentDate(
        @Path("id") id: Int,
        @Path("foreignId") foreignId: Int?,
        @Path("userId") userId: Int,
        @Body visitDate: VisitDate
    ) : Response<Unit>


    @DELETE("/visitDates/{id}/{userId}")
    suspend fun clearDoneDate(
        @Path("id") id : Int,
        @Path("userId") userId : Int
    ) : Response<Unit>

    @DELETE("/visitDates/{id}/{userId}")
    suspend fun clearPredictedDate(
        @Path("id") id : Int,
        @Path("userId") userId : Int
    ) : Response<Unit>

    @DELETE("/visitDates/{id}/{userId}")
    suspend fun clearAppointmentDate(
        @Path("id") id : Int,
        @Path("userId") userId : Int
    ) : Response<Unit>

    @GET("/visitDates/predictedDates")
    suspend fun getVisitDateByDate(
        @Body  visitDate: VisitDate
    )
}
