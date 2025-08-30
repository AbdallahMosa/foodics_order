package repostiores

import com.foodics.domain.repostiories.ProductRepository
import models.Category
import models.Product
import services.ApiService

class ProductRepositoryImpl(
    private val apiService: ApiService
) : ProductRepository {
    override suspend fun getProducts(): List<Product> {
        return apiService.getProducts()
    }

    override suspend fun getCategories(): List<Category> {
        return apiService.getCategories()
    }
}
