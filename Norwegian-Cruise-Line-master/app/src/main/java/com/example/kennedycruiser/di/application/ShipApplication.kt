package com.example.kennedycruiser.di.application

import android.app.Application
import com.example.kennedycruiser.di.shipModule
import org.koin.core.context.startKoin

class ShipApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(shipModule)
        }
    }
}