package com.example.watchlist.auth.domain.usecase

import com.example.watchlist.auth.domain.provider.AuthProvider
import com.example.watchlist.core.NetworkResponse
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow

class SignInWithEmailUseCase(
    private val provider: AuthProvider
) {
    suspend operator fun invoke(email: String, password: String): Flow<NetworkResponse<FirebaseUser>> {
        return provider.signInWithEmailAndPassword(email, password)
    }
}