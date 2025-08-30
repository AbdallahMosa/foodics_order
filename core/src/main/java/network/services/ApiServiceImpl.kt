package network.services

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class ApiServiceImpl(
    private val client: HttpClient,
    private val baseUrl: String
) : ApiService
{
    override suspend fun getProducts(): List<Any> {
        return client.get("$baseUrl/products").body()
    }
}