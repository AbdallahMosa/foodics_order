package com.foodics.data.di

import com.foodics.core.network.KtorClient
import network.NetworkMonitor
import org.koin.android.ext.koin.androidContext
import services.ApiService
import services.ApiServiceImpl
import org.koin.dsl.module

val networkModule = module {

    single { KtorClient().build() }

    single<ApiService> {
        ApiServiceImpl(
            client = get(),
            baseUrl = "https://my.api.mockaroo.com"
        )
    }

    single { NetworkMonitor(androidContext()) }
}