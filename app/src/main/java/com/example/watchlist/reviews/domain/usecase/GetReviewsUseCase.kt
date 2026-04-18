package com.example.watchlist.reviews.domain.usecase

import com.example.watchlist.reviews.domain.repository.ReviewRepository

class GetReviewsUseCase(
    private val repository: ReviewRepository
) {
    operator fun invoke(userId: String) =
        repository.getReviews(userId)
}