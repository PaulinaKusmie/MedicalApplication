package com.example.composeactivity.api

import com.example.composeactivity.data.dto.ApiResponse
import com.example.composeactivity.data.entity.Examination
import com.example.composeactivity.data.entity.Specjalization
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface SpecjalizationAPI {

    @GET("/specjalizations/")
    suspend fun getAllSpecjalizations() :List<Specjalization>

    @GET("/specjalizations/{userId}")
    suspend fun getSpecjalizations(@Path("userId") userId : Int) : List<Specjalization>

    @PUT("/specjalizations/{userId}/{name}")
    suspend fun addSpecjalization(
        @Path("userId") id: Int,
        @Path("name") name: String
    ):  Response<ApiResponse>

    @DELETE("/specjalizations/{id}/{userId}")
    suspend fun deleteSpecjalization(
        @Path("id") id: Int,
        @Path("userId") userId: Int
    ):  Response<Boolean>
}