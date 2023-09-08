package com.submission.news.data.firebase

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.submission.news.data.firebase.remote.FirebaseApi
import kotlinx.coroutines.runBlocking

class FirebaseDataStore(private val fetch : FirebaseApi) : FirebaseRepository {
    override suspend fun checkSignIn(): Boolean {
        return fetch.checkSignIn()
    }

    override suspend fun logout() {
        fetch.logout()
    }

    override suspend fun signInFirebase(email: String, password: String): Task<AuthResult>  {
        return fetch.signInFirebase(email, password)
    }

    override suspend fun signUpFirebase(email: String, password: String): Task<AuthResult> {
        return fetch.signUpFirebase(email, password)
    }
}