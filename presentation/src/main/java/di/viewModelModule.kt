package di

import com.foodics.presentation.MenuViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MenuViewModel(getProductsUseCase = get(), getCategoriesUseCase = get()) }


}