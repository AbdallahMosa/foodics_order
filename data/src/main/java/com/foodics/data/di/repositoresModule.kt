package com.foodics.data.di

import com.foodics.domain.repostiories.ProductRepository
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module
import repostiores.ProductRepositoryImpl

val repositoriesModule = module {
    single<ProductRepository> {
        ProductRepositoryImpl(
            apiService = get(),
            localDataSource = get(),
            dispatcher = Dispatchers.IO
        )
    }
}