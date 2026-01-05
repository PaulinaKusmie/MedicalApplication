package com.example.composeactivity.repository

import android.util.Log
import com.example.composeactivity.api.UserAPI
import com.example.composeactivity.data.dto.ApiResponse
import com.example.composeactivity.data.dto.ConfirmAccountRequest
import com.example.composeactivity.data.dto.LoginRequest
import com.example.composeactivity.data.entity.User
import retrofit2.Response
import javax.inject.Inject

class UserRepository @Inject constructor(private val userAPI: UserAPI) {


    suspend fun login(requestLogin: LoginRequest): Response<User> = userAPI.login(requestLogin)

    suspend fun register (requestUser: User) = userAPI.register(requestUser)

    suspend fun getUser(userId: Int) = userAPI.getUser(userId)

    suspend fun forgotPassword(email: String) = userAPI.forgotPassword(email)

    suspend fun confirmCode(requestAccountRequest: ConfirmAccountRequest) : Response<Unit> = userAPI.confirmCode(requestAccountRequest)
}

