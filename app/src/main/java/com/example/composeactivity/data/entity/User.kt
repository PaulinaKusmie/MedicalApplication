package com.example.composeactivity.data.entity

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: Int,
    val email: String,
    val name: String,
    val age: Int,
    val password: String,
    val created: String?)
