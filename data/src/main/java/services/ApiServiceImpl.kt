package services

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import models.Category
import models.Product

class ApiServiceImpl(
    private val client: HttpClient,
    private val baseUrl: String
) : ApiService
{
    override suspend fun getProducts(): List<Product> {
        return client.get("$baseUrl/products").body()
    }

    override suspend fun getCategories(): List<Category> {
        return client.get("$baseUrl/categories").body()
    }
}