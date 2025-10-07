package com.example.composeactivity.data.entity

import kotlinx.serialization.Serializable


@Serializable
data class Specjalization(
    val id: Int,
    val name: String,
    val isActive: Boolean,
    val sex: Int, // 0=all, 1=women, 2=men
    val isPay: Boolean
)

