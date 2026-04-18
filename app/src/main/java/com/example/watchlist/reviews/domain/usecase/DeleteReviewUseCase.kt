package com.example.watchlist.reviews.domain.usecase

import com.example.watchlist.reviews.domain.repository.ReviewRepository

class DeleteReviewUseCase(
    private val repository: ReviewRepository
) {
    suspend operator fun invoke(id: String) =
        repository.deleteReview(id)
}