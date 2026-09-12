package com.example.pmdassignment1.data

data class TravelLog(
    val id: String,
    val destinationId: String,
    val destinationTitle: String,
    val note: String,
    val rating: Int? = null,
    val timestamp: Long = System.currentTimeMillis()
)
