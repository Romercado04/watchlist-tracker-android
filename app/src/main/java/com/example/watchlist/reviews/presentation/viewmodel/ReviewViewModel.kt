package com.example.watchlist.reviews.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.watchlist.core.data.SessionDataStore
import com.example.watchlist.reviews.domain.model.Review
import com.example.watchlist.reviews.domain.usecase.AddReviewUseCase
import com.example.watchlist.reviews.domain.usecase.DeleteReviewUseCase
import com.example.watchlist.reviews.domain.usecase.GetReviewsUseCase
import com.example.watchlist.reviews.domain.usecase.UpdateReviewUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ReviewViewModel(
    private val getReviews: GetReviewsUseCase,
    private val addReview: AddReviewUseCase,
    private val deleteReview: DeleteReviewUseCase,
    private val updateReview: UpdateReviewUseCase,
    private val sessionDataStore: SessionDataStore
) : ViewModel() {

    private val _reviews = MutableStateFlow<List<Review>>(emptyList())
    val reviews: StateFlow<List<Review>> = _reviews

    init {
        viewModelScope.launch {
            sessionDataStore.userFlow.collect { user ->
                user?.let {
                    getReviews(it.uid).collect { list ->
                        _reviews.value = list
                    }
                }
            }
        }
    }

    fun addReview(title: String, description: String, rating: Float, userId: String) {
        viewModelScope.launch {
            addReview(
                Review(
                    title = title,
                    description = description,
                    rating = rating,
                    userId = userId
                )
            )
        }
    }

    fun deleteReview(id: String) {
        viewModelScope.launch {
            deleteReview(id)
        }
    }
}