package com.example.composeactivity.repository

import android.util.Log
import com.example.composeactivity.api.UserAPI
import com.example.composeactivity.data.dto.ConfirmAccountRequest
import com.example.composeactivity.data.dto.ConfirmResponse
import com.example.composeactivity.data.dto.LoginRequest
import com.example.composeactivity.data.entity.User
import retrofit2.Response
import javax.inject.Inject

class UserRepository @Inject constructor(private val userAPI: UserAPI) {


    suspend fun login(requestLogin: LoginRequest): Response<ConfirmResponse> = userAPI.login(requestLogin)

    suspend fun register (requestUser: User): Response<ConfirmResponse>  = userAPI.register(requestUser)

    suspend fun getUser(userId: Int) = userAPI.getUser(userId)

    suspend fun forgotPassword(email: String) = userAPI.forgotPassword(email)

    suspend fun confirmCode(requestAccountRequest: ConfirmAccountRequest) : Response<ConfirmResponse> = userAPI.confirmCode(requestAccountRequest)
}

