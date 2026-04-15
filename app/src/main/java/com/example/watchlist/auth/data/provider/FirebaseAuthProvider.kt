package com.example.watchlist.auth.data.provider

import com.example.watchlist.auth.domain.provider.AuthProvider
import com.example.watchlist.core.NetworkResponse
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class FirebaseAuthProvider(
    private val auth: FirebaseAuth
) : AuthProvider {

    override suspend fun signInWithGoogle(credential: AuthCredential): Flow<NetworkResponse<FirebaseUser>> =
        flow {
            emit(NetworkResponse.Loading())
            try {
                val authResult = auth.signInWithCredential(credential).await()
                val user = authResult.user
                if (user != null) {
                    emit(NetworkResponse.Success(user))
                } else {
                    emit(NetworkResponse.Failure("User not found"))
                }
            } catch (e: Exception) {
                emit(NetworkResponse.Failure(e.message ?: "Unknown error"))
            }
        }

    override suspend fun signOut() = auth.signOut()

    override suspend fun getCurrentUser(): FirebaseUser? = auth.currentUser

    override suspend fun signInWithEmailAndPassword(
        email: String,
        password: String
    ): Flow<NetworkResponse<FirebaseUser>> = flow {
        emit(NetworkResponse.Loading())
        try {
            val authResult = auth.signInWithEmailAndPassword(email, password).await()
            val user = authResult.user
            if (user != null) {
                emit(NetworkResponse.Success(user))
            } else {
                emit(NetworkResponse.Failure("User not found"))
            }
        } catch (e: Exception) {
            emit(NetworkResponse.Failure(e.message ?: "Unknown error"))
        }
    }

    override suspend fun createUserWithEmailAndPassword(
        name: String,
        email: String,
        password: String
    ): Flow<NetworkResponse<FirebaseUser>> = flow {
        emit(NetworkResponse.Loading())

        try {
            val authResult = auth.createUserWithEmailAndPassword(email, password).await()
            val user = authResult.user

            if (user != null) {
                // associate the user with the provided name
                user.updateProfile(
                    UserProfileChangeRequest.Builder()
                        .setDisplayName(name)
                        .build()
                ).await()

                emit(NetworkResponse.Success(user))
            } else {
                emit(NetworkResponse.Failure("No se pudo crear el usuario"))
            }

        } catch (e: Exception) {
            emit(NetworkResponse.Failure(e.message ?: "Error desconocido"))
        }
    }

}