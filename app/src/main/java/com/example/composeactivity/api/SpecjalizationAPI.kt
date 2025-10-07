package com.example.composeactivity.api

import com.example.composeactivity.data.entity.Specjalization
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Path

interface SpecjalizationAPI {

    @GET("/specjalization/{userId}")
    suspend fun getSpecjalizations(@Path("userId") userId : Int) : List<Specjalization>
}