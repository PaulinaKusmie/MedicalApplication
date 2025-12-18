package com.example.composeactivity.data.entity

import com.example.composeactivity.utils.Named
import kotlinx.serialization.Serializable


@Serializable
data class Specjalization(
    override val id: Int,
    override val name: String,
    val isActive: Boolean,
    val sex: Int, // 0=all, 1=women, 2=men
    val isPay: Boolean,
    override val isVerificated :Boolean
) : Named

