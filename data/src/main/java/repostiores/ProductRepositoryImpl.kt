package repostiores

import com.foodics.domain.repostiories.ProductRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import local.dao.MenuLocalDataSource
import models.Category
import models.Product
import services.ApiService

class ProductRepositoryImpl(
    private val apiService: ApiService,
    private val localDataSource: MenuLocalDataSource,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ProductRepository {
    override suspend fun getProducts(): List<Product> = withContext(dispatcher) {
        try {
            val remoteProducts = apiService.getProducts()
            localDataSource.saveProducts(remoteProducts)
            remoteProducts
        } catch (e: Exception) {
            localDataSource.getProducts()
        }
    }

    override suspend fun getCategories(): List<Category> = withContext(dispatcher) {
        try {
            val remoteCategories = apiService.getCategories()
            localDataSource.saveCategories(remoteCategories)
            remoteCategories
        } catch (e: Exception) {
            localDataSource.getCategories()
        }
    }
}
