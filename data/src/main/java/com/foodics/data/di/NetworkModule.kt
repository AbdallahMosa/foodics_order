package com.foodics.data.di

import com.foodics.core.network.KtorClient
import network.services.ApiService
import network.services.ApiServiceImpl
import org.koin.dsl.module

val networkModule = module {

    single { KtorClient().build() }

    single<ApiService> {
        ApiServiceImpl(
            client = get(),
            baseUrl = "https://your-mock-api.com/api"
        )
    }
}