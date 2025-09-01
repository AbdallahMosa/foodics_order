package local.dao

import models.Category
import models.Product

interface MenuLocalDataSource {
    suspend fun getCategories(): List<Category>
    suspend fun saveCategories(categories: List<Category>)
    suspend fun getProducts() : List<Product>
    suspend fun saveProducts(products: List<Product>)
    suspend fun clearAllData()
}