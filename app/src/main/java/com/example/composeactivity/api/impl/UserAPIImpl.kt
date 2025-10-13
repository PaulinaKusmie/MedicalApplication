package com.example.composeactivity.api.impl

import com.example.composeactivity.api.UserAPI
import com.example.composeactivity.data.dto.ApiResponse
import com.example.composeactivity.data.dto.LoginRequest
import com.example.composeactivity.data.entity.User
import retrofit2.Response
import javax.inject.Inject


public class UserAPIImpl @Inject constructor() : UserAPI {
    override suspend fun login(request: LoginRequest): Response<User> {
        TODO("Not yet implemented")
    }
    override suspend fun register(requestUser: User): Response<User> {
        TODO("Not yet implemented")
    }

    override suspend fun getUser(userId: Int): Response<User> {
        TODO("Not yet implemented")
    }

    override suspend fun forgotPassword(email: String): Response<ApiResponse> {
        TODO("Not yet implemented")
    }
}