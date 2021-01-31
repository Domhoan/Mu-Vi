package com.example.muvi

import android.app.Application
import com.example.muvi.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MainApp)
            modules(
                listOf(
                    networkModule,
                    viewModelModule,
                    apiModule,
                    dbModule,
                    favoriteRepoModule,
                    movieRepoModule
                )
            )
        }
    }
}
