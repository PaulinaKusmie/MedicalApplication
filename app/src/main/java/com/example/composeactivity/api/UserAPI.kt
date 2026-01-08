package com.example.composeactivity.api

import com.example.composeactivity.data.dto.ApiResponse
import com.example.composeactivity.data.dto.ConfirmAccountRequest
import com.example.composeactivity.data.dto.ConfirmResponse
import com.example.composeactivity.data.dto.LoginRequest
import com.example.composeactivity.data.entity.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import javax.inject.Inject

interface UserAPI {

    @POST("users/login")
    suspend fun login(@Body request: LoginRequest): Response<ConfirmResponse>

    @POST("users/register")
    suspend fun register(@Body requestUser: User): Response<ConfirmResponse>

    @GET("user/{id}")
    suspend fun getUser(@Path("id") userId: Int): Response<User>

    @POST("users/{email}")
    suspend fun forgotPassword(@Path("email") email: String): Response<ApiResponse>

    @POST("users/confirmCode")
    suspend fun confirmCode(@Body requestAccountRequest: ConfirmAccountRequest): Response<ConfirmResponse>
}


