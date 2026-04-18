package com.example.watchlist.reviews.domain.usecase

import com.example.watchlist.reviews.domain.model.Review
import com.example.watchlist.reviews.domain.repository.ReviewRepository

class AddReviewUseCase(
    private val repository: ReviewRepository
) {
    suspend operator fun invoke(review: Review) =
        repository.addReview(review)
}