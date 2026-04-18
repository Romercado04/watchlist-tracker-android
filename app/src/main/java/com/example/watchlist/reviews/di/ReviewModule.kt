package com.example.watchlist.reviews.di


import com.example.watchlist.reviews.data.repository.ReviewRepositoryImpl
import com.example.watchlist.reviews.domain.repository.ReviewRepository
import com.example.watchlist.reviews.domain.usecase.AddReviewUseCase
import com.example.watchlist.reviews.domain.usecase.DeleteReviewUseCase
import com.example.watchlist.reviews.domain.usecase.GetReviewsUseCase
import com.example.watchlist.reviews.domain.usecase.UpdateReviewUseCase
import com.example.watchlist.reviews.presentation.viewmodel.ReviewViewModel
import com.google.firebase.firestore.FirebaseFirestore
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val reviewModule = module {

    // Firebase
    single { FirebaseFirestore.getInstance() }

    // Repository
    single<ReviewRepository> { ReviewRepositoryImpl(get()) }

    // UseCases
    single { GetReviewsUseCase(get()) }
    single { AddReviewUseCase(get()) }
    single { DeleteReviewUseCase(get()) }
    single { UpdateReviewUseCase(get()) }

    // ViewModel
    viewModel {
        ReviewViewModel(
            getReviews = get(),
            addReview = get(),
            deleteReview = get(),
            updateReview = get(),
            sessionDataStore = get()
        )
    }
}