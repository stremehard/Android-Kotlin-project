package com.example.viacinema

import android.app.Application
import com.example.viacinema.di.myModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            // Android context
            androidContext(this@MyApplication)
            // myModules
            modules(myModules)
        }
    }
}