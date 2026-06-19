package com.example.composeactivity.data.dto

data class ConfirmResponse(
    val success: Boolean,
    val message: String,
    val id: Int? = null  )