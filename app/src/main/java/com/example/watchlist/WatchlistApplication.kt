package com.example.watchlist

import android.app.Application
import com.example.watchlist.auth.di.authModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class WatchlistApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@WatchlistApplication)
            modules(authModule,
            )
        }
    }
}