package com.example.composeactivity.api

import com.example.composeactivity.data.dto.ApiResponse
import com.example.composeactivity.data.entity.Reminder
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ReminderAPI {



    @GET("/reminders/{userId}")
    suspend fun getReminders(@Path("userId") userId: Int): List<Reminder>

    @PUT("/reminders/{userId}/{id}")
    suspend fun updateReminder(
        @Path("userId") userId: Int,
        @Path("id") id: Int,
        @Body reminder: Reminder
    ):  Response<Unit>

    @PUT("/reminders")
    suspend fun addReminder(@Body reminder: Reminder):  Response<ApiResponse>

    @DELETE("/reminders/{id}/{userId}")
    suspend fun deleteReminder(
        @Path("id") id: Int,
        @Path("userId") userId: Int
    ):  Response<Unit>

}