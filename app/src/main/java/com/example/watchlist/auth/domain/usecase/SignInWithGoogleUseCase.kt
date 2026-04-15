package com.example.watchlist.auth.domain.usecase

import com.example.watchlist.auth.domain.provider.AuthProvider
import com.example.watchlist.core.NetworkResponse
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow

class SignInWithGoogleUseCase(
    private val provider: AuthProvider
) {
    suspend operator fun invoke(credential: AuthCredential): Flow<NetworkResponse<FirebaseUser>> {
        return provider.signInWithGoogle(credential)
    }
}