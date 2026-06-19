package com.example.composeactivity.data.entity
import com.example.composeactivity.utils.Named
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class Examination (
    override val id: Int,
    override val name: String,
    val isActive: Boolean,
    val ageFrom: Int,
    val frequency: Int, // year between examination
    val sex: Int, // 0=all, 1=women, 2=men
    val isPay: Boolean,
    val isRefundable: Boolean,
    val description: String,
    override val isVerificated :Boolean
 ) : Named