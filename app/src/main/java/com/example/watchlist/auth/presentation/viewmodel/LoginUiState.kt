package com.example.watchlist.auth.presentation.viewmodel

import com.example.watchlist.auth.domain.models.UserModel

data class LoginUiState(
    val isLoading: Boolean = false,
    val user: UserModel? = null,
    val error: String? = null
)
