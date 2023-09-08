package com.submission.news.data.firebase

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult

interface FirebaseRepository {

    suspend fun checkSignIn() : Boolean
    suspend fun logout()
    suspend fun signInFirebase(email: String, password: String) : Task<AuthResult>
    suspend fun signUpFirebase(email: String, password: String) : Task<AuthResult>
}