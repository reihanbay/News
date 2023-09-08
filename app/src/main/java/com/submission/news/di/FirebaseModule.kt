package com.submission.news.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import com.submission.news.data.firebase.FirebaseDataStore
import com.submission.news.data.firebase.FirebaseRepository
import com.submission.news.data.firebase.remote.FirebaseApi
import com.submission.news.ui.login.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val firebaseModule = module {
    single { FirebaseApi(FirebaseAuth.getInstance()) }
    single<FirebaseRepository>{ FirebaseDataStore(get())}

    viewModel { LoginViewModel(get()) }
}