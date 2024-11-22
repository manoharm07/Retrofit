package com.example.retrofit.api

data class todo(
    val completed: Boolean,
    val id: Int,
    val title: String,
    val userId: Int
)