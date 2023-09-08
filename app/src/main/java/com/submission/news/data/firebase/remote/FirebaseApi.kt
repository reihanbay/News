package com.submission.news.data.firebase.remote

import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel

class FirebaseApi(val auth: FirebaseAuth) : FirebaseClient {
    override suspend fun checkSignIn(): Boolean {
        val currentUser = auth.currentUser
        return currentUser != null
    }

    override suspend fun logout() {
        auth.signOut()
    }

    override suspend fun signInFirebase(email: String, password: String): Task<AuthResult> = runBlocking {
        val result = Channel<Task<AuthResult>>()
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            launch {
                result.send(it)
            }
        }
        result.receive()
    }

    override suspend fun signUpFirebase(email: String, password: String): Task<AuthResult> = runBlocking{
        val result = Channel<Task<AuthResult>>()
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            launch {
                result.send(it)
            }
        }
        result.receive()
    }
}