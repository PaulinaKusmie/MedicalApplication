package com.example.composeactivity.data.entity
import kotlinx.serialization.Serializable

@Serializable
data class SpecjalizationUser(
    val id: Int,
    val userId: Int,
    val specializationId : Int
)

