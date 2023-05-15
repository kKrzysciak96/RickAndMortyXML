package com.example.rickandmortyxml.core.applications

import android.app.Application
import com.example.rickandmortyxml.core.di.koinInjector
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class KoinApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin()
    }

    fun startKoin() {
        startKoin {
            androidContext(this@KoinApp)
            modules(koinInjector)
        }
    }
}