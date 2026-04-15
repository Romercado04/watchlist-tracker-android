package com.example.watchlist.auth.domain.usecase

import com.example.watchlist.auth.domain.provider.AuthProvider

class SignOutUseCase(private val authProvider: AuthProvider) {
    suspend operator fun invoke() = authProvider.signOut()
}