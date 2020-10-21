package com.dicoding.movieapp

import android.app.Application
import com.dicoding.movieapp.core.di.databaseModule
import com.dicoding.movieapp.core.di.networkModule
import com.dicoding.movieapp.core.di.repositoryModule
import com.dicoding.movieapp.di.useCaseModule
import com.dicoding.movieapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}