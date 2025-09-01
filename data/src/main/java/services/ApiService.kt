package services

import models.Category
import models.Product

interface ApiService {
    suspend fun getProducts(): List<Product>

    suspend fun getCategories() : List<Category>
}