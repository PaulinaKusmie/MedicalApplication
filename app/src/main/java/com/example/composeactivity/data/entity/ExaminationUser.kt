package com.example.composeactivity.data.entity

import kotlinx.serialization.Serializable

@Serializable
data class ExaminationUser(
    val id: Int,
    val userId: Int,
    val examinationId : Int
)

