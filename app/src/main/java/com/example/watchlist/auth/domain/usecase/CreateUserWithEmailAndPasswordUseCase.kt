package com.example.watchlist.auth.domain.usecase

import com.example.watchlist.auth.domain.provider.AuthProvider

class CreateUserWithEmailAndPasswordUseCase(private val authProvider: AuthProvider) {
    suspend operator fun invoke(name: String, email: String, password: String) =
        authProvider.createUserWithEmailAndPassword(name, email, password)
}