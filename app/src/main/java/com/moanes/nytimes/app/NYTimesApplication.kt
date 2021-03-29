package com.moanes.nytimes.app

import android.app.Application
import com.facebook.stetho.Stetho
import com.moanes.nytimes.modules.appModules
import com.moanes.nytimes.modules.repoModule
import com.moanes.nytimes.modules.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class NYTimesApplication:Application() {
    override fun onCreate() {
        super.onCreate()

        Stetho.initializeWithDefaults(this)

        startKoin {
            androidContext(this@NYTimesApplication)
            modules(listOf(appModules, repoModule, viewModelModule))
        }
    }
}