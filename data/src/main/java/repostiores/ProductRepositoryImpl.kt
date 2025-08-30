package repostiores

import com.foodics.domain.repostiories.ProductRepository
import network.services.ApiService

class ProductRepositoryImpl(
    private val apiService: ApiService
) : ProductRepository {
    override suspend fun getProducts(): List<Any> {
        return apiService.getProducts()
    }
}
