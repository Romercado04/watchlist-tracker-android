package com.example.watchlist.reviews.data.repository

import com.example.watchlist.reviews.domain.model.Review
import com.example.watchlist.reviews.domain.repository.ReviewRepository
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlin.collections.emptyList

class ReviewRepositoryImpl(
    private val firestore: FirebaseFirestore
) : ReviewRepository {

    override fun getReviews(userId: String) = callbackFlow {

        val listener = firestore.collection("reviews")
            .whereEqualTo("userId", userId)
            .addSnapshotListener { snapshot, _ ->

                val reviews = snapshot?.documents?.mapNotNull {
                    it.toObject(Review::class.java)
                } ?: emptyList()

                trySend(reviews)
            }

        awaitClose { listener.remove() }
    }

    override suspend fun addReview(review: Review) {
        val doc = firestore.collection("reviews").document()

        val reviewWithId = review.copy(id = doc.id)

        doc.set(reviewWithId)
    }

    override suspend fun updateReview(review: Review) {
        firestore.collection("reviews")
            .document(review.id)
            .set(review)
    }

    override suspend fun deleteReview(id: String) {
        firestore.collection("reviews")
            .document(id)
            .delete()
    }
}