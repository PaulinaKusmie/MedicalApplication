package com.example.composeactivity.repository

import android.util.Log
import com.example.composeactivity.api.UserAPI
import com.example.composeactivity.data.dto.ApiResponse
import com.example.composeactivity.data.dto.LoginRequest
import com.example.composeactivity.data.entity.User
import retrofit2.Response
import javax.inject.Inject

class UserRepository @Inject constructor(private val userAPI: UserAPI) {

    init {
        Log.d("UserRepository", "userAPI injected: $userAPI")
        if (userAPI == null) Log.e("UserRepository", "userAPI is null! Injection failed!")
    }

    suspend fun login(requestLogin: LoginRequest): Response<User> {
        Log.d("UserRepository", "Login API call for: ${requestLogin.email}")

    return userAPI.login(requestLogin)

    }

    suspend fun register (requestUser: User) = userAPI.register(requestUser)

    suspend fun getUser(userId: Int) = userAPI.getUser(userId)

    suspend fun forgotPassword(email: String) = userAPI.forgotPassword(email)
}

//interface UserRepository {
//
//    suspend fun login(requestLogin: LoginRequest) : Response<User>
//
//    suspend fun register (requestUser: User) : Response<User>
//
//    suspend fun getUser(userId: Int) : Response<User>
//
//    suspend fun forgotPassword(email: String): Response<ApiResponse>
//}