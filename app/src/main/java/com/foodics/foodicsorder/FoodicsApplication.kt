package com.foodics.foodicsorder

import android.app.Application
import com.foodics.data.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class FoodicsApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@FoodicsApplication)
            modules(
                networkModule
            )

        }
    }
}