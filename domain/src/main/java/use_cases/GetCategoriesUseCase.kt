package use_cases

import com.foodics.domain.repostiories.ProductRepository
import models.Category

class GetCategoriesUseCase(private val repository: ProductRepository) {
    suspend operator fun invoke(): List<Category> {
        return repository.getCategories()
    }
}