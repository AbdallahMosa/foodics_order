package com.foodics.domain.repostiories

import models.Category
import models.Product

interface ProductRepository {
    suspend fun getProducts(): List<Product>
    suspend fun getCategories():  List<Category>
}