package use_cases

import com.foodics.domain.repostiories.ProductRepository
import models.Product
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetProductsUseCase(private val repository: ProductRepository) {
    suspend operator fun invoke(): List<Product> {
        return repository.getProducts()
    }
}