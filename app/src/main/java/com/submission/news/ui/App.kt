package com.submission.news.ui

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.submission.news.di.apiModule
import com.submission.news.di.firebaseModule
import com.submission.news.di.newsModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        startKoin {
            androidContext(this@App)
            modules(listOf(
                apiModule(),
                newsModule,
                firebaseModule
            ))
        }
    }
}