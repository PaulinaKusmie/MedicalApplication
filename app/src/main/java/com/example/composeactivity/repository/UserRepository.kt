package com.example.composeactivity.repository

import com.example.composeactivity.api.UserAPI
import com.example.composeactivity.data.dto.LoginRequest
import com.example.composeactivity.data.entity.User

class UserRepository(private val userAPI: UserAPI) {

    suspend fun login(requestLogin: LoginRequest) = userAPI.login(requestLogin)

    suspend fun register (requestUser: User) = userAPI.register(requestUser)

    suspend fun getUser(userId: Int) = userAPI.getUser(userId)

    suspend fun forgotPassword(request: LoginRequest) = userAPI.forgotPassword(request)
}