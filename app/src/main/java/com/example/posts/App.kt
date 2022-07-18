package com.example.posts

import android.app.Application
import com.example.posts.di.application
import org.koin.core.context.startKoin

class App:Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(listOf(application))
        }
    }
}