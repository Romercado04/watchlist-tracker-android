package com.example.watchlist.reviews.domain.model

data class Review(
    val id: String = "",
    val userId: String = "",
    val title: String = "",
    val description: String = "",
    val rating: Float = 0f,
    val createdAt: Long = System.currentTimeMillis()
)
