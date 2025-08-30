package di

import org.koin.dsl.module
import use_cases.GetCategoriesUseCase
import use_cases.GetProductsUseCase

val useCaseModule = module {
    single { GetProductsUseCase(get()) }
    single { GetCategoriesUseCase(get()) }
}