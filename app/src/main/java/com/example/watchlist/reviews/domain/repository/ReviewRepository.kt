package com.example.watchlist.reviews.domain.repository

import com.example.watchlist.reviews.domain.model.Review
import kotlinx.coroutines.flow.Flow

interface ReviewRepository {

    fun getReviews(userId: String): Flow<List<Review>>

    suspend fun addReview(review: Review)

    suspend fun updateReview(review: Review)

    suspend fun deleteReview(id: String)
}