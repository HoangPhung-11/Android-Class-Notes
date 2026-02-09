package com.example.petstore

import android.app.Application
import com.example.petstore.di.appModules
import org.koin.core.context.GlobalContext.startKoin

class PetsStore: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(appModules)
        }
    }
}