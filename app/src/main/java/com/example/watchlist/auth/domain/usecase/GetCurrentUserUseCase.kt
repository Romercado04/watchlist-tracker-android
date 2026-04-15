package com.example.watchlist.auth.domain.usecase

import com.example.watchlist.auth.domain.provider.AuthProvider
import com.google.firebase.auth.FirebaseUser

class GetCurrentUserUseCase(private val authProvider: AuthProvider) {
    suspend operator fun invoke(): FirebaseUser? = authProvider.getCurrentUser()
}