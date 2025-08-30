package com.foodics.data.di

import com.foodics.domain.repostiories.ProductRepository
import org.koin.dsl.module
import repostiores.ProductRepositoryImpl

val repositoriesModule = module {
    single<ProductRepository> { ProductRepositoryImpl(get()) }
}