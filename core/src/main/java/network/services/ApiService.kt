package network.services

interface ApiService {
    suspend fun getProducts(): List<Any>
}