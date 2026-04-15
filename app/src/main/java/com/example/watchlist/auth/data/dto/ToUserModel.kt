package com.example.watchlist.auth.data.dto

import com.example.watchlist.auth.domain.models.UserModel
import com.google.firebase.auth.FirebaseUser

fun FirebaseUser.toUserModel() = UserModel(
    uid = this.uid,
    name = this.displayName,
    email = this.email
)