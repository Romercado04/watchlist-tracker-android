package com.example.watchlist.auth.di

import com.example.watchlist.auth.data.provider.FirebaseAuthProvider
import com.example.watchlist.auth.domain.provider.AuthProvider
import com.example.watchlist.auth.domain.usecase.CreateUserWithEmailAndPasswordUseCase
import com.example.watchlist.auth.domain.usecase.GetCurrentUserUseCase
import com.example.watchlist.auth.domain.usecase.SignInWithEmailUseCase
import com.example.watchlist.auth.domain.usecase.SignInWithGoogleUseCase
import com.example.watchlist.auth.domain.usecase.SignOutUseCase
import com.example.watchlist.auth.presentation.viewmodel.AuthViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val authModule = module {

    // Firebase Auth instance
    single<FirebaseAuth> { Firebase.auth }

    // Providers
    single<AuthProvider> { FirebaseAuthProvider(auth = get()) }

    // Use cases
    factory { SignInWithEmailUseCase(provider = get()) }
    factory { SignInWithGoogleUseCase(provider = get()) }
    factory { SignOutUseCase(authProvider = get()) }
    factory { GetCurrentUserUseCase(authProvider = get()) }
    factory { CreateUserWithEmailAndPasswordUseCase(authProvider = get()) }

    // ViewModel
    viewModel {
        AuthViewModel(
            signInWithEmail = get(),
            signInWithGoogle = get(),
            signOut = get(),
            getCurrentUser = get(),
            createUserWithEmail = get(),
            sessionDataStore = get()
        )
    }
}