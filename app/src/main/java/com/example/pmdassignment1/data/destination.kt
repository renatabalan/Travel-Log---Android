package com.example.pmdassignment1.data

import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
import androidx.compose.runtime.Composable

data class Destination(
    val id: String,
    val title: String,
    val country: String,
    val summary: String,
    val icon: @Composable () -> Unit
)
