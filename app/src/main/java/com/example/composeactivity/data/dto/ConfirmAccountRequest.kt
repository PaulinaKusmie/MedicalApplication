package com.example.composeactivity.data.dto

data class ConfirmAccountRequest (
    var email : String,
    val code : Int
)