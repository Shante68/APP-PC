package com.example.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "saved_builds")
data class SavedBuildEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val buildName: String,
    val purposeName: String, // Name of UsePurpose enum
    val totalPrice: Double,
    val totalWattage: Int,
    val cpuId: String?,
    val gpuId: String?,
    val moboId: String?,
    val ramId: String?,
    val storageId: String?,
    val psuId: String?,
    val caseId: String?,
    val coolerId: String?,
    val createdAt: Long = System.currentTimeMillis(),
    val notes: String = ""
)
