package com.foodics.domain.repostiories

interface ProductRepository {
    suspend fun getProducts(): List<Any>
}